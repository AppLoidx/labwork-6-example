package main;

import entities.Gender;
import entities.Human;
import entities.User;
import network.URLCode;
import network.client.Client;
import network.client.UnauthorizedException;

import java.util.Scanner;


public class ClientApp {
    private Client client;
    private boolean isActive = true;

    public void connect(String url, int port) {
        client = new Client(url, port);
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
                try {

                    if (req.matches("login\\s+.+\\s+.+")){
                        String[] ctx = req.split(" ");
                        app.client.setUser(new User(ctx[1], ctx[2]));
                        if (URLCode.decode(app.client.sendCommand("check")).equals("success")){
                            System.out.println("You are registered successfully");
                            continue;
                        } else {
                            app.client.setUser(new User(null, null));
                            System.out.println("Invalid login and password");
                            continue;
                        }
                    }

                    if (req.trim().equals("add")) {
                        System.out.println(URLCode.decode(app.client.sendCommand(req, new Human("Sasha", 170, Gender.FEMALE))));
                    } else {
                        System.out.println(URLCode.decode(app.client.sendCommand(req)));
                    }
                } catch (UnauthorizedException e){
                    System.out.println("Пожалуйста авторизуйтесь комадной login <username> <password>");
                }
            }

        }
    }

    public boolean isActive(){
        return isActive;
    }
}
