package model.mail;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Represents a victim
 *
 * @author JEANNERET Hugo
 * @author LOGAN Victoria
 */
public class Person {

    private String firstName;
    private String lastName;
    private final String address;

    public Person(String address) {
        this.address = address;
        Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
        Matcher matcher = pattern.matcher(address);
        boolean found = matcher.find();

        if(found) {
            //
            this.firstName = matcher.group(1);
            firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);

            //
            this.lastName = matcher.group(2);
            lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAddress() {
        return address;
    }
}
