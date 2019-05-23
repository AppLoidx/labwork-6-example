package commands;

import entities.PersonHashSet;

/**
 * @author Arthur Kupriyanov
 */
public class CommandHandler {
    private static String handleCommand(String line, Commander commander, PersonHashSet col, int lastHashcode){
    if (line.trim().equals("help")){
        return help();
    }
    String res = commander.execute(line);
    if (col.hashCode() != lastHashcode){
        col.setChangedDate();
    }

    return res;
}

    private static String help(){

        // TODO: Дописать HELP
        return "Help : ";
    }

}
