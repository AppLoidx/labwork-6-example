package network;

import network.handlers.Handler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Arthur Kupriyanov
 */
public class Server implements Runnable{
    private final ServerSocket socket;
    private Handler handler;
    private boolean isEnabled = false;


    public Server(final ServerSocket sc){
        socket = sc;
    }
    Server setHandler(Handler handler){
        if (handler!=null) this.handler = handler;

        return this;
    }
    public void setEnabled(boolean isEnabled){
        this.isEnabled = isEnabled;
    }

    private synchronized void listen(final Handler handler) throws IOException {
            Socket ioSocket = socket.accept();

            new Thread(() -> {
                try(BufferedReader br = new BufferedReader(new InputStreamReader(ioSocket.getInputStream()));
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ioSocket.getOutputStream()))){
                String request = URLCode.decode(br.readLine());
                String response = handler.getResponse(request);
                    writeData(bw, response == null ? "NULL" : response);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        ioSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
//            String request = URLCode.decode(br.readLine());
//            String response = handler.getResponse(request);
//            writeData(bw, response == null ? "NULL" : response);
        }



    private void writeData(BufferedWriter bw, String data) throws IOException {

            bw.write(URLCode.encode(data));
            bw.flush();
    }

    @Override
    public void run() {
        isEnabled = true;
        while(isEnabled){

            try {
                this.listen(handler);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
