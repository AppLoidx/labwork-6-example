package ds.jdbc;

import entities.PersonHashSet;
import entities.User;

import java.sql.*;

/**
 * @author Arthur Kupriyanov
 */
public class UserDB {
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private Connection connection;
    public UserDB() throws SQLException {
        connection = Database.getConnection();
    }

    public void saveUser(User user) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());

        stmt.execute();
    }

    public boolean checkUserExist(String username) throws SQLException {
        return getUserByUsername(username)!=null;
    }

    public User getUserByUsername(String username) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username='" + username + "'");

        if (resultSet.next()){
            String password = resultSet.getString(PASSWORD);
            User user = new User(username, password);
            PersonHashSet set = new CollectionDB().getCollectionByOwner(user.getUsername());
            set.setOwner(user.getUsername());
            user.setPersonHashSet(set);
            return user;
        }

        return null;
    }

}
