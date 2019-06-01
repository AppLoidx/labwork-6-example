package network;


import network.handlers.Handler;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Запускает сервер на указанном порту с помощью метода {@link #launch(int, Handler)}
 *
 * @author Arthur Kupriyanov
 */
public class ServerLauncher {
    public static void launch( int port, Handler handler){
        System.out.println("Starting server ...");
        try {
            Thread serverThread = new Thread(new Server(new ServerSocket(port)).setHandler(handler));
            serverThread.start();
            System.out.println("Started server on port : " + port + "\nThread name : " + serverThread.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void launchSerialServer(int port, Handler handler){
        System.out.println("Starting server ...");
        try {
            Thread serverThread = new Thread(new BadServer(new ServerSocket(port)).setHandler(handler));
            serverThread.start();
            System.out.println("Started serialize server on port : " + port + "\nThread name : " + serverThread.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
