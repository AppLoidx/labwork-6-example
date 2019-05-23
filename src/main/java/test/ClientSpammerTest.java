package test;

import main.Application;
import network.client.Client;
import network.client.highload.ClientFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Arthur Kupriyanov
 */
class ClientSpammerTest {

    private static Thread createThread(Runnable runnable){
        return new Thread(runnable);
    }
    private static String[] commands =
            new String[]{"add", "add-if-min", "add-if-max", "remove 140", "remove where name=natasha"};

    public static void main(String ... args) {
            Application app = new Application();
            app.launch();
            System.out.println("launching application");

            List<Client> clients = ClientFactory.getClients(50, "localhost", 8888);
            Runnable r = () -> new ArrayList<>(clients).forEach(client -> {
                try {
                    System.out.println(client.sendData("info"));
//                    System.out.println(client.sendData(commands[4]));
//                    System.out.println(client.sendData(commands[0]));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            for (int i = 0; i < 100; i++) {
                new Thread(r).start();
            }


//        for (Client c : clients){
//            try {
//                System.out.println(c.sendData("info"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}