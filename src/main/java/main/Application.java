package main;

import commands.Commander;
import controls.CollectionEditor;
import entities.CSVWriteable;
import entities.PersonHashSet;
import network.Server;
import network.ServerLauncher;
import network.handlers.RequestHandler;

import java.io.File;
import java.io.IOException;

/**
 * Основное приложение сервера
 *
 * @author Arthur Kupriyanov
 */
public class Application {

    private  final static String path2CSV ;
    static {
        String path = System.getenv("LAB_INPUT_PATH");
        if (path == null) path2CSV = "src\\main\\resources\\data.csv";  // default value
        else path2CSV = path;   // value from environment
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.launch();
    }

    /**
     * Запуск сервера
     */
    public void launch() {

        // initializing empty collection
        PersonHashSet col = new PersonHashSet();

        // import data from files if path exist

        if (path2CSV!=null)
            System.out.println(CollectionEditor.addPersonsFromCSV(col, new File(path2CSV)));


        // adding web hook for emergency save collection

        setWebhookForSaveCollection(col, "data/saved-data.csv");

        // initializing commands

        Commander commander = new Commander(col);

        // launching server

        ServerLauncher.launch(8888, new RequestHandler(col ,commander));

        // launching serializable server
        ServerLauncher.launchSerialServer(666, new RequestHandler(col, commander));

    }

    private void setWebhookForSaveCollection(CSVWriteable obj, String pathToSave){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                obj.saveTo(new File(pathToSave));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    public static String getPath2CSV(){
        return path2CSV;
    }

}
