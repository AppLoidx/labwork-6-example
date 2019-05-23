package commands.concrete;

import commands.CollectionAction;
import commands.Command;


/**
 * @author Arthur Kupriyanov
 */
public class Show extends Command {
    private String decor = "--------------------\n";
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public CollectionAction getAction(String context) {

        return col -> {
            StringBuilder sb = new StringBuilder();
            col.forEach(e -> sb.append(decor).append(e).append("\n").append(decor));
            return sb.toString();
        };
    }


}
