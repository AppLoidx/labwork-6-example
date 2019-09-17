package network;


import network.handlers.Handler;

import java.io.IOException;
import java.net.ServerSocket;


public class ServerLauncher {

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
