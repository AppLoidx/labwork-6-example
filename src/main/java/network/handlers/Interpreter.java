package network.handlers;

import com.google.gson.GsonBuilder;
import entities.Person;
import entities.PersonData;

/**
 * @author Arthur Kupriyanov
 */
public class Interpreter {
    public static String addPerson(Person p){
        PersonData data = new PersonData(p);
        return "add " + new GsonBuilder().create().toJson(data);
    }
}
