package network.handlers;

/**
 * Интерфейс для обработчиков запросов.
 *
 * Все запросы должны обрабатываться с помощью {@link Handler}. Таким образом, наше приложение опять получает
 * гибкость и адаптируемость. Экзмепляры этого класса, следует передавать при создании сервера
 * или менять в Runtime с помощью методов, например, <code>setHandler(Handler h)</code>
 *
 * @author Arthur Kupriyanov
 */
@FunctionalInterface
public interface Handler {
    String getResponse(String data);
}
