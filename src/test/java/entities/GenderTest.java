package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Arthur Kupriyanov
 */
class GenderTest {

    @Test
    void getGender() {
        String[] femaleTrue = new String[]{"female", "feMale", " FEMALE"};
        String[] femaleFalse = new String[]{"femal", "eMale", "FEMALE1"};
        String[] maleTrue = new String[]{"male", "Male", "MALE "};
        String[] maleFalse = new String[]{"female", "M1ale", " M ALE"};

        for (String s : femaleTrue){
            assertEquals(Gender.FEMALE, Gender.getGender(s));
        }

        for (String s : femaleFalse){
            assertNotEquals(Gender.FEMALE, Gender.getGender(s));
        }
        for (String s : maleTrue){
            assertEquals(Gender.MALE, Gender.getGender(s));
        }

        for (String s : maleFalse){
            assertNotEquals(Gender.MALE, Gender.getGender(s));
        }
    }
}