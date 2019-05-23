package commands.concrete;


import commands.CollectionAction;
import commands.Command;



/**
 * @author Arthur Kupriyanov
 */
public class Remove extends Command {

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public CollectionAction getAction(String context) {

        if (context.matches("where\\s*?name\\s*?=.+")){
            String name = context.split("=")[1];
            return deleteByName(name.trim());
        }
        try {
            int width = Integer.parseInt(context);
            return col -> {
                col.removeIf(person -> person.getHeight() == width);
                return "Deleting by width : " + width;
            };
        } catch (NumberFormatException e){
            return (col -> "Неверный синтаксис команды");
        }


    }

    private CollectionAction deleteByName(String name){
        return col -> {
            col.removeIf(person -> person.getName().equals(name));
            return "Deleting by name : " + name;
        };
    }

}
