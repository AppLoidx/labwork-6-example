package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov
 */
class PasswordTest {

    @Test
    void hash() {
        String hashed = Password.hash("123");
        assertTrue(Password.isEqual("123", hashed));
    }
}