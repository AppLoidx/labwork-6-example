package network.handlers;

import commands.Commander;
import ds.jdbc.CollectionDB;
import ds.jdbc.UserDB;
import entities.Person;
import entities.PersonHashSet;
import entities.User;
import network.Message;
import util.Password;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

/**
 *
 * Паттерн делегирование
 *
 */
public class SerialHandler {
    private Handler handler;
    public SerialHandler(Handler handler){
        this.handler = handler;
    }

    public String getResponse(ObjectInputStream is){
        try {

            Object obj = is.readObject();
            if (!(obj instanceof Message)){
                return "Attachment is not instance of Message. Please check your settings";
            }
            Message clientMessage = (Message)obj;

            if (!clientMessage.getMessage().matches("register\\s+.+\\s.+")) {
                if (clientMessage.getUser() == null || clientMessage.getUser().getUsername() == null || clientMessage.getUser().getPassword() == null) {
                    return "You don't attached the user login and password. Please, check your request";
                } else {
                    String username = clientMessage.getUser().getUsername();
                    String password = clientMessage.getUser().getPassword();

                    try {
                        User user = new UserDB().getUserByUsername(username);

                        if (user == null || !Password.isEqual(password, user.getPassword())) {
                            return "Incorrect password. Please re-auth";
                        }

                        clientMessage.setUser(user);
                        // continue the handling operation if user registered or this command equals to "register"

                    } catch (SQLException e) {
                        return e.getMessage();
                    }
                }
            } else {
                clientMessage.getUser().setPersonHashSet(new PersonHashSet());
            }

            handler = new RequestHandler(clientMessage.getUser().getPersonHashSet(), new Commander(clientMessage.getUser().getPersonHashSet()));

            String message;

            if (clientMessage.getAttach()!=null){
                message = messageWithAttach(clientMessage.getMessage(), clientMessage.getAttach());
            } else {
                message = simpleMessage(clientMessage.getMessage());
            }

            PersonHashSet phs = clientMessage.getUser().getPersonHashSet();
            if (phs == null) {
                phs = new PersonHashSet();
                phs.setOwner(clientMessage.getUser().getUsername());
                clientMessage.getUser().setPersonHashSet(phs);
            }

            if (phs.getOwner() == null) phs.setOwner(clientMessage.getUser().getUsername());
            if (phs.getOwner() != null) new CollectionDB().saveCollection(phs);
            return message;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Couldn't execute command! Caused by: " + e.getMessage();
        } catch (SQLException e) {
            return "Ошибка при работе с БД: " + e.getMessage();
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
