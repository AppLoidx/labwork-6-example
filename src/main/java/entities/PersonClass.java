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

        @Override
        public Person getInstance(String name, int height, Gender gender) {
            return new Loli(name, height, gender);
        }
    }, HUMAN{
        @Override
        public Person getInstance(String name, int height, Gender gender, Date birthDate) {
            return new Human(name, height, gender, birthDate);
        }

        @Override
        public Person getInstance(String name, int height, Gender gender) {
            return new Human(name, height, gender);
        }
    };

    public abstract Person getInstance(String name, int height, Gender gender, Date birthDate);
    public abstract Person getInstance(String name, int height, Gender gender);
}
