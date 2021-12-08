package smtp;

import model.mail.Mail;

import java.io.IOException;
import java.util.logging.Logger;

public interface ISmtpClient {
    void sendMail(Mail mail) throws IOException;
}
