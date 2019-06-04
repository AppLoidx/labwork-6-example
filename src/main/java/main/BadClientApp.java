package main;

import entities.Gender;
import entities.Human;
import network.URLCode;
import network.client.BadClient;

import java.util.Scanner;


public class BadClientApp {
    private BadClient client;
    private boolean isActive = true;

    public void connect(String url, int port) {
        client = new BadClient(url, port);
    }
    public static void main(String[] args) {
        BadClientApp app = new BadClientApp();
        Scanner sc = new Scanner(System.in);
        app.connect("localhost", 666);
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
