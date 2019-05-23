package entities;

import java.util.Date;

/**
 * @author Arthur Kupriyanov
 */
public enum PersonClass {
    LOLI{
        @Override
        public Person getInstance(String name, int height, Gender gender, Date birthDate) {
            return new Loli(name, height, gender, birthDate);
        }
    }, HUMAN{
        @Override
        public Person getInstance(String name, int height, Gender gender, Date birthDate) {
            return new Human(name, height, gender, birthDate);
        }};

    public abstract Person getInstance(String name, int height, Gender gender, Date birthDate);

}
