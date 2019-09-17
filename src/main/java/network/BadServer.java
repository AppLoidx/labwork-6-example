package network;


import network.handlers.SerialHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class BadServer extends Server {
    public BadServer(ServerSocket sc) {
        super(sc);
    }

    private void handle(SerialHandler handler, Socket ioSocket){
        try(ObjectInputStream ioStream = new ObjectInputStream(ioSocket.getInputStream())){

            String response = handler.getResponse(ioStream);

            // TODO: запись в потоки ввода вывода
            writeDataFromStdIO(ioSocket, response);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ioSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeDataFromStdIO(Socket ioSocket, String data) throws IOException {
        PrintStream oldOut = System.out;
        PrintStream socketPS = new PrintStream(ioSocket.getOutputStream());
        System.setOut(socketPS);

        System.out.println(data);

        socketPS.flush();
        socketPS.close();
        System.setOut(oldOut);
    }

    private synchronized void listen(SerialHandler sHandler) throws IOException {
        Socket ioSocket = socket.accept();
        new Thread(() -> handle(sHandler, ioSocket)).start();
    }

    private synchronized void notMultithreadedListen(SerialHandler sHandler) throws IOException {
        Socket ioSocket = socket.accept();
        handle(sHandler, ioSocket);
    }

    @Override
    public void run() {
        isEnabled = true;
        while(isEnabled){

            try {
                this.listen(new SerialHandler(handler));                   // многопоточная прослушка
//                 this.notMultithreadedListen(new SerialHandler(handler));                   // немногопоточная прослушка
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
