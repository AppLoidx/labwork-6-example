package util;

import entities.Gender;
import entities.Human;
import entities.Loli;
import entities.Person;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Arthur Kupriyanov
 */
class PersonFabricTest {

    @Test
    void getPerson() throws ParseException {
        Person pLoli = PersonFabric.getPerson("loli", "", 12, Gender.MALE, new Date(), null);
        Person pHuman = PersonFabric.getPerson("hUmaN", "", 12, Gender.FEMALE, new Date(), null);
        Person pExcept = null;
        try {
            pExcept = PersonFabric.getPerson("hUmasaN", "", 12, Gender.FEMALE, new Date(), null);
        } catch (Exception ignored){}

        assertTrue(pLoli instanceof Loli);
        assertTrue(pHuman instanceof Human);
        assertNull(pExcept);
    }
}