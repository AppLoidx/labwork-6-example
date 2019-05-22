package entities;

import java.util.Date;

/**
 * @author Arthur Kupriyanov
 */
public class Loli extends Person{
    public Loli(String name, int height, Gender gender, Date birthDate) {
        super(name, height, gender, birthDate);
    }

    public Loli(String name, int height, Gender gender) {
        super(name, height, gender);
    }
}
