package commands;


import commands.concrete.*;
import controls.CollectionEditor;
import main.Application;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Arthur Kupriyanov
 */
public class CommandContainer {
    private static Set<Command> commands = new HashSet<>();
    static {
        commands.add(new Add());
        commands.add(new AddIfMax());
        commands.add(new AddIfMin());
        commands.add(new Info());
        commands.add(new Remove());
        commands.add(new RemoveLast());
        commands.add(new Show());
        commands.add(new Save());
        commands.add(new Import());
    }

    // Пример добавления через анонимный класс и лямбда функцию

    static {
        commands.add(new Command("load", (col)->{
            String path2CSV = Application.getPath2CSV();
            if (path2CSV!=null) {
                CollectionEditor.addPersonsFromCSV(col, new File(path2CSV));
                return "Persons added from file: " + path2CSV;
            }else {
                return "Can't load persons";
            }
        }));
    }

    public static Set<Command> getCommands() {
        return new HashSet<>(commands);
    }

//    On runtime command adding method
//
//    public static void addCommand(Command command){
//        commands.add(command);
//    }

    static Command getCommand(String commandName){
        Optional<Command> cmd = commands.stream().filter(c -> c.getName().equals(commandName)).findFirst();
        return cmd.orElse(null);
    }
}
