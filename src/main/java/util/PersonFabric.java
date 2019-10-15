package util;

import entities.Gender;
import entities.Person;
import entities.PersonClass;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Паттерн абстрактная фабрика для класса {@link Person}
 *
 */
public class PersonFabric {

    public static Person getPerson(String classOf, String name, int height, Gender gender, Date birthDate, String helloMessage) throws ParseException{
        if (birthDate==null) birthDate = new Date();
        for (PersonClass pc : PersonClass.values()){
            if (pc.name().toLowerCase().equals(classOf.toLowerCase())){
                Person p =  pc.getInstance(name, height, gender, birthDate, LocalDateTime.now());
                p.setHelloMessage(helloMessage);
                return p;
            }
        }

        throw new ParseException("Can't parse person", 0);
    }
}
