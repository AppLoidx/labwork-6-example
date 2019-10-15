package entities;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Enum позволяющий создавать конкретные объекты. Используется в {@link util.PersonFabric}
 *
 */
public enum PersonClass {
    LOLI{
        @Override
        public Person getInstance(String name, int height, Gender gender, Date birthDate, LocalDateTime localDateTime) {
            return new Loli(name, height, gender, birthDate, localDateTime);
        }
    }, HUMAN{
        @Override
        public Person getInstance(String name, int height, Gender gender, Date birthDate, LocalDateTime localDateTime) {
            return new Human(name, height, gender, birthDate, localDateTime);
        }};

    public abstract Person getInstance(String name, int height, Gender gender, Date birthDate, LocalDateTime localDateTime);

}
