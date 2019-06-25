package test;

import main.Application;
import network.client.Client;
import network.client.highload.ClientFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



class ClientSpammerTest {
    private static volatile Date startTime;
    private static final int clientsCountDefault = 50;
    private static final int threadsCountDefault = 20;

    public static void main(String ... args) {
            Application app = new Application();
            Thread serverThread = new Thread(app::launch);

            serverThread.setDaemon(true);
            serverThread.start();
            System.out.println("launching application");

            int port;
            int clientsCount;
            int threadsCount;

            try {
                port = Integer.parseInt(System.getProperty("port", "8888"));

                clientsCount = Integer.parseInt(System.getProperty("clients", "25"));
                threadsCount = Integer.parseInt(System.getProperty("threads", "7"));
            } catch (NumberFormatException e){
                System.err.println("Вы ввели неверный формат. Ожидался Integer\n" +
                        e.getMessage());
                return;
            }
            List<Client> clients = ClientFactory.getClients(clientsCount, "localhost", port);

            Runnable r = () -> new ArrayList<>(clients).forEach(client -> {
                try {
                    System.out.println(client.sendData("info"));
                    System.out.println(client.sendData("info"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            startTime = new Date();
            Runtime.getRuntime().addShutdownHook(
                    new Thread(
                            () -> System.out.println(
                                    "\n\n======== DEBUG INFO ========\n" +
                                    "Operation took " + (new Date().getTime() - startTime.getTime()) + "ms\n" +
                                            "Clients : " + clientsCount + "\n" +
                                            "Threads : " + threadsCount + "\n" +
                                            "Operation delay : 50ms" +
                                    "\n============================")
                    ));

            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < threadsCount; i++) {
                threads.add(new Thread(r));
                threads.get(i).start();
            }

    }
}