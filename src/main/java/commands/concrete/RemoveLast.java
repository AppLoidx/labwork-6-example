package commands.concrete;


import commands.CollectionAction;
import commands.Command;
import entities.Person;

import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
public class RemoveLast extends Command {

    @Override
    public String getName() {
        return "remove-last";
    }

    @Override
    public CollectionAction getAction(String context) {
        return (col) -> {
            if (col.isEmpty()){
                return "Collection is empty";
            }
            List<Person> list = col.getSortedList();

            Person p = list.get(list.size()  - 1);
            col.remove(p);
            return "Removed person with name : " + p.getName();
        };
    }

}
