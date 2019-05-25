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

            data = dataPreHandle(data);
            if (data==null){
                return "Command not executed";
            }
            writeData(URLCode.encode(data), socketWriter);
            return URLCode.decode(socketReader.readLine());
        } catch (ConnectException e){
            System.err.println("Не удалось отправить запрос: " + e.getMessage());
            try {
                Thread.sleep(100);
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
        return path==null?"src/main/resources/data.csv": path;
    }

}
