package entities;

/**
 * @author Arthur Kupriyanov
 */
public enum Gender {
    MALE{
        @Override
        public String toString() {
            return "Парень";
        }
    },
    FEMALE{
        @Override
        public String toString() {
            return "Девушка";
        }
    },
    UNKNOWN{
        @Override
        public String toString() {
            return "Неизвестно";
        }
    };

    public static Gender getGender(String gender){
        for (Gender g : Gender.values()){
            if (g.name().toLowerCase().equals(gender.trim().toLowerCase())){
                return g;
            }
        }

        return UNKNOWN;
    }
}
