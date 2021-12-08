import config.ConfigurationManager;
import model.mail.Mail;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        ConfigurationManager configurationManager = new ConfigurationManager();
        PrankGenerator prankGenerator = new PrankGenerator(configurationManager);
        List<Prank> pranks = prankGenerator.generatePranks();

        SmtpClient smtpClient = new SmtpClient(configurationManager.getSmtpServerAddress(), configurationManager.getSmtpServerPort());

        for (Prank p : pranks) {
            Mail mail   = new Mail();
            String[] to = new String[p.getVictimRecipients().size()];
            String[] cc = new String[p.getWitnessRecipients().size()];

            for (int i = 0; i < p.getVictimRecipients().size(); ++i)
                to[i] = p.getVictimRecipients().get(i).getAddress();

            for (int i = 0; i < p.getWitnessRecipients().size(); ++i)
                cc[i] = p.getWitnessRecipients().get(i).getAddress();

            mail.setFrom(p.getVictimSender().getAddress());
            mail.setTo(to);
            mail.setCc(cc);
            mail.setBody(p.getMessage());

            smtpClient.sendMail(mail);
        }
    }
}
