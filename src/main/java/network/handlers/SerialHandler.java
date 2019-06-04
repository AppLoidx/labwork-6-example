package network.handlers;

import entities.Person;
import network.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * Паттерн делегирование
 *
 */
public class SerialHandler {
    private final Handler handler;
    public SerialHandler(Handler handler){
        this.handler = handler;
    }

    public String getResponse(ObjectInputStream is){
        try {
            System.out.println("123123123123");
            System.out.println(is);
            Object obj = is.readObject();
            System.out.println(obj);
            if (!(obj instanceof Message)){
                return "Attachment is not instance of Message. Please check your settings";
            }
            Message clientMessage = (Message)obj;

            System.out.println(clientMessage.getMessage());

            if (clientMessage.getAttach()!=null){
                return messageWithAttach(clientMessage.getMessage(), clientMessage.getAttach());
            } else {
                return simpleMessage(clientMessage.getMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Couldn't execute command! Caused by: " + e.getMessage();
        }
    }
    private String messageWithAttach(String request, Object p){
        if (p instanceof Person){
            Person person = (Person) p;

            if (request.toLowerCase().trim().equals("add")){
                return handler.getResponse(Interpreter.addPerson(person));
            } else {
                return simpleMessage(request);
            }

        } else {
            return "Not allowed type of object : " + p.getClass().getSimpleName();
        }
    }

    private String simpleMessage(String request){
        return handler.getResponse(request);
    }

}
