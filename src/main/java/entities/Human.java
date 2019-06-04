package entities;

import java.util.Date;


public class Human extends Person {
    public Human(String name, int height, Gender gender, Date birthDate) {
        super(name, height, gender, birthDate);
    }

    public Human(String name, int height, Gender gender) {
        super(name, height, gender);
    }
}
