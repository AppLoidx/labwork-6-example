package entities;

import java.io.File;
import java.io.IOException;

/**
 * @author Arthur Kupriyanov
 */
public interface CSVWriteable {
    void saveTo(File file) throws IOException;
}
