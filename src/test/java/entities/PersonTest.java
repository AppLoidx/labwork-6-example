package entities;


import org.junit.jupiter.api.Test;
import util.parser.PersonReader;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Arthur Kupriyanov
 */
class PersonTest {

    @Test
    void CSVData() throws Exception {
        List<Person> list;
        list = PersonReader.readPersons(new File("src/test/java/util/parser/test.csv"));

        List<String> lines = new ArrayList<>();
        for (Person p : list){
            lines.add(p.CSVData());
        }

        List<Person> persons = new ArrayList<>();
        for (String l : lines){
            try {
                persons.add(PersonReader.parsePerson(l));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        assertEquals(list, persons);
    }
}