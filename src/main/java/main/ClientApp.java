package main;

import network.client.Client;

import java.io.IOException;
import java.util.Scanner;

/**
 * Основное приложение клиента
 *
 * @author Arthur Kupriyanov
 */
public class ClientApp {
    private Client client;
    private boolean isActive = true;

    public void connect(String url, int port) {
        client = new Client(url, port);
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Отправка и получение ответа от сервера
     *
     * Метод выполняется поверх метода {@link Client#sendData(String)}
     * @param req отправляемый запрос
     * @return ответ сервера
     */
    public String getResponse(String req) {
        if (client == null) {
            return "Client not connected";
        }

        try {
            return client.sendData(req);
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    public static void main(String[] args) {
        ClientApp app = new ClientApp();
        Scanner sc = new Scanner(System.in);
        app.connect("localhost", 8888);
        while (app.isActive()) {
            String req = sc.nextLine();
            if (req.equals("exit")) {
                break;
            } else {
                System.out.println(app.getResponse(req));
            }

        }
    }
}
