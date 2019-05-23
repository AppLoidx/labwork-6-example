package network;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Arthur Kupriyanov
 */
public class URLCode {
    public static String decode(String text){
        try {
            return URLDecoder.decode(text, "utf-8");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return text;
        }
    }

    public static String encode(String text){
        try {
            return URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e){
            System.err.println(e.getMessage());
            return text;
        }
    }
}
