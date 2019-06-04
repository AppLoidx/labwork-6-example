package network.client.highload;


import network.client.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Фабрика клиентов для тестирования сервера на нагруженность
 *
 */
public class ClientFactory {
    public static List<Client> getClients(int count, String host, int port){
        List<Client> list = new ArrayList<>();
        while(count-- > 0){
            list.add(new Client(host, port));
        }
        return list;
    }
}
