package main;

import commands.Commander;
import controls.CollectionEditor;
import entities.CSVWriteable;
import entities.PersonHashSet;
import network.ServerLauncher;
import network.handlers.RequestHandler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Основное приложение сервера
 *
 */
public class Application {

    private  final static String path2CSV ;

    static {
        path2CSV = System.getProperty("dataPath", "dataServer.csv" );

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
        int port = getPortFromSysProperty("port", 5123);

        //ServerLauncher.launch(port, new RequestHandler(col ,commander));

        // launching serializable server
        ServerLauncher.launchSerialServer(port, new RequestHandler(col, commander));

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

    public static int getPortFromSysProperty(String key,int def){
        try{
            return Integer.parseInt(System.getProperty(key));
        } catch (Exception e){
            return def;
        }
    }

}
