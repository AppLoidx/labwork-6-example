package network;

import network.handlers.Handler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server implements Runnable{
    final ServerSocket socket;
    protected Handler handler;
    protected boolean isEnabled = false;

    final ExecutorService executorService = Executors.newCachedThreadPool();

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
            new Thread(() -> handle(handler, ioSocket)).start();
        }
    /**
     * Не многопоточная прослушка
     */
    synchronized void notMultithreadedListen(final Handler handler) throws IOException {
        Socket ioSocket = socket.accept();
        handle(handler, ioSocket);
    }

    private void handle(Handler handler, Socket ioSocket) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(ioSocket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ioSocket.getOutputStream()))){
        String request = URLCode.decode(br.readLine());
        String response = handler.getResponse(request);

            writeData(bw, response == null ? "NULL" : response);
//            writeDataFromStdIO(ioSocket, response);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ioSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*
            Расскомментировав строку ниже, можно увидеть, что сервер многопоточный.
            Для этого достаточно запустить ClientSpammer и посмотреть 50 выводов
            результата выполнения команд.
             */

            // Расскомментировать   ----------------------------
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
            // -------------------------------------------------

        }
    }





    void writeData(BufferedWriter bw, String data) throws IOException {
            bw.write(URLCode.encode(data));
            bw.flush();
    }



    @Override
    public void run() {
        isEnabled = true;
        while(isEnabled){

            try {
                 this.listen(handler);                   // многопоточная прослушка (543мс при 2 потоках)
//                 this.notMultithreadedListen(handler);   // немногопоточная прослушка
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
