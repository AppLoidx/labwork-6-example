package test;

import main.Application;
import network.client.Client;
import network.client.highload.ClientFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Arthur Kupriyanov
 */
class ClientSpammerTest {

    public static void main(String ... args) {
            Application app = new Application();
            app.launch();
            System.out.println("launching application");

            List<Client> clients = ClientFactory.getClients(50, "localhost", 8888);

            Runnable r = () -> new ArrayList<>(clients).forEach(client -> {
                try {
                    client.sendData("info");
                    client.sendData("info");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < 150; i++) {
                threads.add(new Thread(r));
                threads.get(i).start();
            }

    }
}