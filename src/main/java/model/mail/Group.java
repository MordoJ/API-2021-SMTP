package model.mail;

import java.util.ArrayList;
import java.util.List;

/** Represents the group of victims of our prank
 *
 * @author JEANNERET Hugo
 * @author LOGAN Victoria
 */
public class Group {

    private final List<Person> members = new ArrayList<>();

    public void addMember(Person person) {
        members.add(person);
    }

    public List<Person> getMembers() {
        return new ArrayList<>(members);
    }

}
