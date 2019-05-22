package entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.parser.PersonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov
 */
class PersonTest {

    @Test
    void CSVData() throws FileNotFoundException {
        PersonReader pr = new PersonReader();
        List<Person> list = pr.readPersons(new File("src/test/java/util/parser/test.csv"));
        List<String> lines = new ArrayList<>();
        for (Person p : list){
            lines.add(p.CSVData());
        }
        
        List<Person> persons = new ArrayList<>();
        for (String l : lines){
            try {
                persons.add(pr.parsePerson(l));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        assertEquals(list, persons);
    }
}