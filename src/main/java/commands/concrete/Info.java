package commands.concrete;


import commands.CollectionAction;
import commands.Command;
import entities.Person;

import java.text.SimpleDateFormat;

/**
 * @author Arthur Kupriyanov
 */
public class Info extends Command {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public CollectionAction getAction(String context) {

        return col -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Дата изменения: ").append(new SimpleDateFormat("dd.MM HH:mm:ss").format(col.getChangedDate()));
            sb.append("\n");
            sb.append("Хэш код: ").append(col.hashCode());
            StringBuilder persons = new StringBuilder();
            for (Person p : col){
                persons.append(p.getName()).append("[").append(p.getClass().getSimpleName()).append("]").append("; ");
            }
            sb.append("\nУчастники коллекции:\n").append(col.isEmpty() ? "Объектов еще нет" : persons.toString());


            return sb.toString();
        };

    }


}
