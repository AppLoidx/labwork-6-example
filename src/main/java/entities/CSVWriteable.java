package entities;

import java.io.File;
import java.io.IOException;

/**
 * Интерфейс предоставляющий метод для сохранения сущности.
 *
 */
public interface CSVWriteable {
    void saveTo(File file) throws IOException;
}
