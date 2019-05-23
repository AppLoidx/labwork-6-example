package network.client;

import network.URLCode;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

/**
 * @author Arthur Kupriyanov
 */
public class Client {
    private final String HOST;
    private final int PORT;


    public Client(String host, int port) {
        HOST = host;
        PORT = port;
    }

    public String sendData(String data) throws IOException {
        try (Socket clientSocket = new Socket(HOST, PORT);
             BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
             BufferedReader socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            writeData(URLCode.encode(data), socketWriter);
            return URLCode.decode(socketReader.readLine());
        } catch (ConnectException e){
            System.err.println(e.getMessage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            return sendData(data);
        }
    }

    private void writeData(String data, final BufferedWriter socketWriter) throws IOException {
            socketWriter.write(data.toCharArray());
            socketWriter.write("\n");
            socketWriter.flush();
    }

}
