package commands.concrete;

import commands.CollectionAction;
import commands.Command;

/**
 * @author Arthur Kupriyanov
 */
public class Check extends Command {
    @Override
    public String getName() {
        return "check";
    }

    @Override
    public CollectionAction getAction(String context) {
        return (col) -> "success";
    }
}
