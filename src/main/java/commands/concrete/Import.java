package commands.concrete;

import com.sun.javafx.fxml.PropertyNotFoundException;
import commands.CollectionAction;
import commands.Command;
import entities.Person;
import util.parser.PersonReader;

import java.text.ParseException;


public class Import extends Command {
    @Override
    public String getName() {
        return "import";
    }

    @Override
    public CollectionAction getAction(String context) {
        return col -> {

            StringBuilder log = new StringBuilder();
            String[] dataLines = context.split("\n");
            for (String s : dataLines){
                try {
                    Person p = PersonReader.parsePerson(s);
                    if (p!=null){
                        boolean res = col.add(p);
                        if (res) log.append("Person ").append(p.getName()).append(" added!\n");
                        else log.append("Person ").append(p.getName()).append(" not added because this person exist!\n");
                    }
                } catch (ParseException | PropertyNotFoundException e) {
                    log.append(e.getMessage()).append("\n");
                }
            }

            return log.toString();
        };
    }
}
