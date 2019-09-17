package network.client;

import entities.Person;
import network.Message;

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
                // TODO: через сетевой канал
                sChannel.connect(new InetSocketAddress(HOST, PORT));
            } catch (ConnectException ce){
                // TODO: Обработка временной недоступности
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
        Message message = new Message(dataPreHandle(command));
            return sendData(message);

    }
    public String sendCommand(String command, Person p){
        Message message = new Message(command , p);
            return sendData(message);
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

    private String dataPreHandle(String data){
        if (data.equals("import")){
            File file = new File(getDataFilePath());
            if (file.exists() && file.canRead()){
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    StringBuilder fileContext = new StringBuilder();
                    while((line = br.readLine())!=null){
                        fileContext.append(line).append("\n");
                    }

                    return "import " + fileContext.toString();
                } catch (IOException e) {
                    System.err.println("Can't load file to server : " + e.getMessage());
                    return null;
                }
            } else {
                System.err.println("File doesn't exist or permission denied!");
                return null;
            }
        } else {
            return data;
        }
    }

    private String getDataFilePath(){
        String path = System.getenv("LAB_DATA_FILE");
        return path==null?"dataImport.csv": path;
    }

}
