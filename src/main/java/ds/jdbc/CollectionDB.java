package ds.jdbc;

import com.google.gson.GsonBuilder;
import entities.PersonHashSet;

import java.sql.*;

/**
 * @author Arthur Kupriyanov
 */
public class CollectionDB {
    private final String OWNER = "owner";
    private final String DATA = "data";
    private Connection connection;

    public CollectionDB() throws SQLException {
        connection = Database.getConnection();
    }

    public PersonHashSet getCollectionByOwner(String username) throws SQLException {
        String sql = "SELECT * FROM collections WHERE owner='" + username + "'";

        ResultSet resultSet = connection.createStatement().executeQuery(sql);

        if (resultSet.next()){
            String data = resultSet.getString(DATA);
            return new GsonBuilder().create().fromJson(data, PersonHashSet.class);
        }
        PersonHashSet people = new PersonHashSet();
        people.setOwner(username);
        return people;
    }

    public void saveCollection(PersonHashSet set) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM collections where owner='" + set.getOwner() + "'");
        if (rs.next()){
            PreparedStatement stmt = connection.prepareStatement("UPDATE collections SET data=? WHERE owner=?");
            stmt.setString(2, set.getOwner());
            stmt.setString(1, new GsonBuilder().create().toJson(set));
            stmt.execute();
        } else {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO collections (owner, data) VALUES (?, ?)");
            stmt.setString(1, set.getOwner());
            stmt.setString(2, new GsonBuilder().create().toJson(set));
            stmt.execute();
        }
    }
}
