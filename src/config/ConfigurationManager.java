package config;

import model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/** Responsible of recovering the configuration
 *
 * @author JEANNERET Hugo
 * @author LOGAN Victoria
 */
public class ConfigurationManager implements IConfigurationManager {

    private String smtpServerAddress;
    private int smtpServerPort;
    private final List<Person> victims;
    private final List<String> messages;
    private int numberOfGroups;
    private List<Person> witnessesToCc;

    public ConfigurationManager() throws IOException {
        victims  = loadAddressFromFile("src/config/victims.RES.utf8");
        messages = loadMessagesFromFile("src/config/messages.utf8");
        loadProperties("src/config/config.properties");
    }

    private void loadProperties(String filename) throws IOException {
        try (FileInputStream fis   = new FileInputStream(filename)) {
            Properties properties = new Properties();
            properties.load(fis);
            this.smtpServerAddress = properties.getProperty("smtpServerAddress");
            this.smtpServerPort    = Integer.parseInt(properties.getProperty("smtpServerPort"));
            this.numberOfGroups    = Integer.parseInt(properties.getProperty("numberOfGroups"));

            this.witnessesToCc = new ArrayList<>();
            String witnesses   = properties.getProperty("witnessesToCc");
            String[] witnessesAddresses = witnesses.split(",");
            for(String address : witnessesAddresses)
                this.witnessesToCc.add(new Person(address));
        }
    }

    private List<Person> loadAddressFromFile(String fileName) throws IOException {
        List<Person> result;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try (BufferedReader reader = new BufferedReader(isr)) {
                result = new ArrayList<>();
                String address = reader.readLine();
                while(address != null) {
                    result.add(new Person(address));
                    address = reader.readLine();
                }
            }
        }
        return result;
    }

    private List<String> loadMessagesFromFile(String fileName) throws IOException {
        List<String> result;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try (BufferedReader reader = new BufferedReader(isr)) {
                result = new ArrayList<>();
                String line = reader.readLine();
                while(line != null) {
                    StringBuilder body = new StringBuilder();
                    while((line != null) && (!line.equals("--"))) {
                        body.append(line);
                        body.append("\r\n");
                        line = reader.readLine();
                    }
                    result.add(body.toString());
                    line = reader.readLine();
                }
            }
        }
        return result;
    }

    @Override
    public List<Person> getVictims() {
        return victims;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    @Override
    public List<Person> getWitnessesToCc() {
        return witnessesToCc;
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }
}
