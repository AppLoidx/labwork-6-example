package util.parser;

import com.sun.javafx.fxml.PropertyNotFoundException;
import entities.Gender;
import entities.Person;
import util.PersonFabric;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author Arthur Kupriyanov
 */
public class PersonReader {
    public static List<Person> readPersons(File file) throws Exception {
        ArrayList<Person> persons = new ArrayList<>();
        Scanner sc = new Scanner(new FileInputStream(file));
        while (sc.hasNext()){
            try {
                persons.add(parsePerson(sc.nextLine()));
            } catch (ParseException e){
                System.err.println("Ошибка при чтении персонажа " + e.getMessage());
            }
        }

        return persons;
    }

    public static Person parsePerson(String line) throws PropertyNotFoundException, ParseException {
        String classOf = CSVReader.readString("classOf", line);
        int height = Integer.parseInt(CSVReader.readString("height", line).trim());
        String name = CSVReader.readString("name", line).trim();
        String helloMessage = CSVReader.readString("helloMessage", line, null);
        String gender = CSVReader.readString("gender" , line, null);
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(CSVReader.readString("birthDate", line));
        } catch (ParseException e) {
            System.err.println("Can't parse date : " + e.getMessage());
        }


        return PersonFabric.getPerson(classOf, name, height, Gender.getGender(gender), date, helloMessage);
    }
}
