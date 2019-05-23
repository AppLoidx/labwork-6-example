package network;


import network.handlers.Handler;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Arthur Kupriyanov
 */
public class ServerLauncher {
    public static void launch( int port, Handler handler){
        try {
            new Thread(new Server(new ServerSocket(port)).setHandler(handler)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
