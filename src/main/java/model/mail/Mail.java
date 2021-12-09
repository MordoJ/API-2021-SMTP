package model.mail;

/** Represents the mail we want to send as a prank
 *
 * @author JEANNERET Hugo
 * @author LOGAN Victoria
 */
public class Mail {

    private String from;
    private String[] to; // there can be more than one recipient
    private String[] cc; // there can be more than one witness
    private String body;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
