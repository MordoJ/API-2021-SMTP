package config;

import model.mail.Person;

import java.util.List;

public interface IConfigurationManager {
    List<Person> getVictims();
    List<String> getMessages();
    int getNumberOfGroups();
    List<Person> getWitnessesToCc();
}
