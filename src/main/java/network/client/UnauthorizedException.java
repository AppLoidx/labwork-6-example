package network.client;

/**
 * @author Arthur Kupriyanov
 */
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        this("Ошибка авторизации", new RuntimeException(), false, false);
    }

    protected UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, false);
    }
}
