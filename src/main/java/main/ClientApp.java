package main;

import entities.Gender;
import entities.Human;
import network.URLCode;
import network.client.BadClient;

import java.util.Scanner;


public class ClientApp {
    private BadClient client;
    private boolean isActive = true;

    public void connect(String url, int port) {
        client = new BadClient(url, port);
    }
    public static void main(String[] args) {
        ClientApp app = new ClientApp();
        Scanner sc = new Scanner(System.in);
        String host;
        int port;
        try {
            host = System.getProperty("host", "localhost");
            port = Integer.parseInt(System.getProperty("port", "5123"));
        } catch (Exception e){
            System.err.println("Ошибка " + e.getMessage() + "\nБудут использованы параметры по умолчанию");
            host = "localhost";
            port = 5123;
        }
        app.connect(host, port);
        while (app.isActive()) {
            String req = sc.nextLine();
            if (req.equals("exit")) {
                break;
            } else {
                if (req.trim().equals("add")) {
                    System.out.println(URLCode.decode(app.client.sendCommand(req, new Human("Sasha", 170, Gender.FEMALE))));
                } else {
                    System.out.println(URLCode.decode(app.client.sendCommand(req)));
                }
            }

        }
    }

    public boolean isActive(){
        return isActive;
    }
}
