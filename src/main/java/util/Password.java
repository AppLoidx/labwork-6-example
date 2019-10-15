package util;

import org.apache.commons.codec.digest.Md5Crypt;

import java.util.Base64;
import java.util.Date;

/**
 * @author Arthur Kupriyanov
 */
public class Password {
    private static final String salt = Base64.getEncoder().encodeToString("hochuSdat'Laby".getBytes());
    public static String hash(String password){
        return Md5Crypt.md5Crypt((password + salt).getBytes());
    }
    private static String hash(String password, String anotherEncryptedPassword){
        return Md5Crypt.md5Crypt((password + salt).getBytes(), anotherEncryptedPassword);
    }

    public static boolean isEqual(String rawPassword, String hashedPassword){
        return hashedPassword.equals(hash(rawPassword, hashedPassword));
    }

    public static String generatePassword(String salt){
        return Base64.getEncoder().encodeToString(String.valueOf(salt.hashCode() + (int) new Date().getTime()).getBytes());
    }
}
