package entities;

import java.time.LocalDateTime;
import java.util.Date;


public class Human extends Person {
    public Human(String name, int height, Gender gender, Date birthDate, LocalDateTime localDateTime) {
        super(name, height, gender, birthDate, localDateTime);
    }

    public Human(String name, int height, Gender gender) {
        super(name, height, gender);
    }

    public void beHuman(){
        System.out.println("I am being human! I don't know why I'm doing this thing");
    }
}
