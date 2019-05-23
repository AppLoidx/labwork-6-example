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
        StringBuilder sb = new StringBuilder();
        return col -> {
            sb.append("Дата изменения: ").append(new SimpleDateFormat("dd.MM HH.mm").format(col.getChangedDate()));
            sb.append("\n");
            sb.append("Хэш код: ").append(col.hashCode());
            sb.append("\n");
            for (Person p : col){
                sb.append(p.getName()).append("[").append(p.getClass().getSimpleName()).append("]").append(", ");
            }

            sb.append("\nУчастники коллекции:\n ").append(sb.toString().isEmpty() ? "Объектов еще нет" : sb.toString());

            return sb.toString();
        };

    }


}
