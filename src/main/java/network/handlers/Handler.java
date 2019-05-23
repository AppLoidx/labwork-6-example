package network.handlers;

/**
 * @author Arthur Kupriyanov
 */
@FunctionalInterface
public interface Handler {
    String getResponse(String data);
}
