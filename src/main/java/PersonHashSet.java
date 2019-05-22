import entities.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
public class PersonHashSet extends HashSet<Person> {

    public List<Person> getSortedList(){
        List<Person> persons = new ArrayList<>(this);
        Collections.sort(persons);
        return persons;
    }


}
