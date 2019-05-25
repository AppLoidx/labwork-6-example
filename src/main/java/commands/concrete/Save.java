package commands.concrete;

import commands.CollectionAction;
import commands.Command;

import java.io.File;
import java.io.IOException;

/**
 * @author Arthur Kupriyanov
 */
public class Save extends Command {
    private final String FILENAME = "saved-data.csv";
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public CollectionAction getAction(String context) {


        return col ->{
            String filename = FILENAME;
            if (context.matches(".+")){
                if (context.matches("[a-zA-Zа-яА-Я\\-]+")){
                    filename = context;
                } else {
                    return "Имя файла содержит недопустимы символы. Используйте [a-я][a-z][-]";
                }
            }
            try {
                col.saveTo(new File(filename));
                return "File saved to : " + filename;
            } catch (IOException e) {
                return "Can't save file: " + e.getMessage();
            }
        };
    }
}
