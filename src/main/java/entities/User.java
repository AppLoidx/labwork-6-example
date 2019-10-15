package entities;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author Arthur Kupriyanov
 */
public class User implements Serializable {
    @Expose
    private String username;
    @Expose
    private String password;
    private PersonHashSet personHashSet;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public PersonHashSet getPersonHashSet() {
        return personHashSet;
    }

    public void setPersonHashSet(PersonHashSet personHashSet) {
        this.personHashSet = personHashSet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
