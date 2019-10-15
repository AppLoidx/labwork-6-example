package entities;

import java.time.LocalDateTime;
import java.util.Date;


public class Loli extends Person{
    public Loli(String name, int height, Gender gender, Date birthDate, LocalDateTime localDateTime) {
        super(name, height, gender, birthDate, localDateTime);
    }

    public Loli(String name, int height, Gender gender) {
        super(name, height, gender);
    }

    public void loliSpecialAttack(){
        System.out.println("This attack can do only lolies!");
    }
}
