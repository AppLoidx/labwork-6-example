package network;

import entities.Person;

import java.io.Serializable;
import java.util.Date;

/**
 * Сериализуемый класс, отправляемый клиенту
 */
public class Message implements Serializable {
    private final String msg;
    private Person attach;
    private transient final Date generatedDate;
    {
        generatedDate = new Date();
    }
    public Message(String message){
        this.msg = message;
    }
    public Message(String message , Person attach){
        this.msg = message;
        this.attach = attach;
    }
    public void setAttach(Person p){
        this.attach = p;
    }
    public Person getAttach(){
        return attach;
    }
    public String getMessage(){
        return msg;
    }
    public Date getGeneratedDate(){
        return generatedDate;
    }

}
