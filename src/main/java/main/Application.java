package main;

import commands.Commander;
import controls.CollectionEditor;
import entities.PersonHashSet;
import network.ServerLauncher;
import network.handlers.RequestHandler;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Arthur Kupriyanov
 */
public class Application {

    private  final String path2CSV ;
    {
        String path = System.getenv("LAB_INPUT_PATH");
        if (path == null)
        path2CSV = "src\\main\\resources\\data.csv";
        else path2CSV = path;
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.launch();
    }

    public void launch() {
        PersonHashSet col = new PersonHashSet();

        if (path2CSV!=null)
        CollectionEditor.addPersonsFromCSV(col, new File(path2CSV));    // data from file

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                col.saveTo(new File("data/saved-data.csv"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        Commander commander = new Commander(col);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Runnable runnable = () -> ServerLauncher.launch(8888, new RequestHandler(col, commander));
//        executorService.execute(runnable);

        String line;
        Scanner sc = new Scanner(System.in);
        int lastHashcode = col.hashCode();
        while(!(line=sc.nextLine()).equals("exit")){
            if (line.trim().equals("help")){
                System.out.println("help");
                continue;
            }
            String resp = commander.execute(line);
            if (col.hashCode() != lastHashcode){
                col.setChangedDate();
            }
            System.out.println(resp);
        }
    }

}
