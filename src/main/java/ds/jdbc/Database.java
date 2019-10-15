package ds.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Arthur Kupriyanov
 */
public class Database {
    private final static String databaseUri = "jdbc:postgresql://localhost:5432/loli";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseUri, "postgres", "postgres");
    }

}
