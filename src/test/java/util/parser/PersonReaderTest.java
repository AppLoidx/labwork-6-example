package util.parser;

import entities.Person;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov
 */
class PersonReaderTest {

    @Test
    void readPersons() throws Exception {
        List<Person> list1 = PersonReader.readPersons(new File("src/test/java/util/parser/test.csv"));
        List<Person> list2 = PersonReader.readPersons(new File("src/test/java/util/parser/testWithWrong.csv"));

        assertNotNull(list1);
        for (Person p : list1){
            assertNotNull(p);
            System.out.println("------------------");
            System.out.println(p);
            System.out.println("------------------");
        }

        if (list2!=null){
            for (Person p : list2){
                assertNotNull(p);
                System.out.println("------------------");
                System.out.println(p);
                System.out.println("------------------");
            }
        }
    }
}