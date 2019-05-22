package util.parser;

/**
 * @author Arthur Kupriyanov
 */
public class PersonPropertyNotFoundException extends RuntimeException {
    public PersonPropertyNotFoundException(String message) {
        super(message);
    }

    protected PersonPropertyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, false);
    }
}
