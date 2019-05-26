package network;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Для пересылки информации между сокетами.
 *
 * Кодируется\Декодируется с помощью {@link URLEncoder}\{@link URLDecoder} из пакета {@link java.net}
 *
 * @author Arthur Kupriyanov
 */
public class URLCode {
    /**
     * @return Декодированную строку
     */
    public static String decode(String text){
        try {
            return URLDecoder.decode(text, "utf-8");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return text;
        }
    }

    /**
     * @return Кодированную строку
     */
    public static String encode(String text){
        try {
            return URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e){
            System.err.println(e.getMessage());
            return text;
        }
    }
}
