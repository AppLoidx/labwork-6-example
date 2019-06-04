package network.client;

import entities.Person;
import network.Message;
import util.Serializator;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class BadClient {
    private final String HOST;
    private final int PORT;

    public BadClient(String host, int port){
        HOST = host;
        PORT = port;
    }

    private String sendData(Serializable obj){
        try(SocketChannel sChannel = SocketChannel.open()){
            try {
                sChannel.connect(new InetSocketAddress(HOST, PORT));
            } catch (ConnectException ce){
                return "Can't connect to server. Caused by : " + ce.getMessage() + "\nPlease try again";
            }

            while(sChannel.isConnectionPending()){
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            }
            if (!sChannel.isConnected()){
                System.out.println("Can't connect to server. Please try again...");
            }

            sChannel.write(prepareBuffer(obj));

            BufferedReader br = new BufferedReader(new InputStreamReader(sChannel.socket().getInputStream()));
            return br.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка : " + e.getMessage());
        }

        return null;
    }

    public String sendCommand(String command){
        Message message = new Message(command);
//        try {
            return sendData(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Can't send command.\nException: " + e.getMessage() +
//                    "Command: " + message.getMessage();
//        }
    }
    public String sendCommand(String command, Person p){
        Message message = new Message(command , p);
//        try {
            return sendData(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Can't send command.\nException: " + e.getMessage() +
//                    "Command: " + message.getMessage();
//        }
    }

    private ByteBuffer prepareBuffer(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        ByteBuffer bf = ByteBuffer.allocate(baos.size());
        bf.put(baos.toByteArray());
        bf.flip();
        return bf;
    }

}
