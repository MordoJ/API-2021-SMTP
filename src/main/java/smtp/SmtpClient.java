package smtp;

import model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

/** Represents the client wanting to send an email through SMTP server
 *
 * @author JEANNERET Hugo
 * @author LOGAN Victoria
 */
public class SmtpClient implements ISmtpClient {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private final String smtpServerAddress;
    private int smtpServerPort;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SmtpClient(String smtpServerAddress, int smtpServerPort) {
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort    = smtpServerPort;
    }

    @Override
    public void sendMail(Mail mail) throws IOException {
        LOG.info("Sending mail via SMTP");
        socket = new Socket(smtpServerAddress, smtpServerPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String line = reader.readLine();
        LOG.info(line);
        writer.printf("EHLO localhost\r\n");
        line = reader.readLine();
        LOG.info(line);
        if (!line.startsWith("250")) {
            throw new IOException("SMTP error: " + line);
        }
        while (line.startsWith("250-")) {
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("MAIL FROM:");
        writer.write(mail.getFrom());
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);

        for (String to : mail.getTo()) {
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        for (String to : mail.getCc()) {
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("DATA");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        writer.write("Content-type: text/plain; charset=\"utf-8\"\r\n");
        writer.write("From: " + mail.getFrom() + "\r\n");

        writer.write("To: " + mail.getTo()[0]);
        for (int i = 1; i < mail.getTo().length; ++i)
            writer.write(", " + mail.getTo()[i]);
        writer.write("\r\n");

        writer.write("Cc: " + mail.getCc()[0]);
        for (int i = 1; i < mail.getCc().length; ++i)
            writer.write(", " + mail.getCc()[i]);
        writer.write("\r\n");

        writer.flush();
        LOG.info(mail.getBody());
        writer.write(mail.getBody());
        writer.write("\r\n");
        writer.write(".");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        writer.write("QUIT\r\n");
        writer.flush();
        reader.close();
        writer.close();
        socket.close();
    }
}














