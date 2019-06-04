package util.parser;

import com.sun.javafx.fxml.PropertyNotFoundException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Парсер строки CSV для значений:
 *
 * <blockquote>
 *     .., ключ=значение, ...
 * </blockquote>
 *
 * Работает со строкой в формате CSV
 *
 */
public class CSVReader {
    static String readString(String field, String data) throws PropertyNotFoundException{
        // ленивый квантор .+?
        String regex = ",*?[ ]*" + field + "\\s*?=\\s*?.*?(,+|$)";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data);
        if (m.find()) return m.group().split("=")[1].replace(",", "");
        else throw new PropertyNotFoundException("Поле " + field + " не найдено!");
    }

    static String readString(String field, String data, String defaultVal){
        try{
            return  readString(field, data);
        } catch (PropertyNotFoundException e){
            return defaultVal;
        }
    }
}
