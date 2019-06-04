package entities;

import com.google.gson.annotations.SerializedName;

/**
 *
 * POM object for deserialize entity from JSON-formatted input
 *
 */
public class PersonData {
    @SerializedName("name")
    private String name;
    @SerializedName("height")
    private int height;
    @SerializedName("class")
    private String classOf;
    @SerializedName("hello_message")
    private String helloMessage;
    @SerializedName("birth_date")
    private String birthDate;

    @SerializedName("gender")
    private String gender;

    public String getGender() {
        return gender;
    }
    public String getName() {
        return name;
    }
    public int getHeight() {
        return height;
    }
    public String getClassOf() {
        return classOf;
    }
    public String getHelloMessage() {
        return helloMessage;
    }
    public String getBirthDate() {
        return birthDate;
    }

    public PersonData(Person p) {
        this.name = p.getName();
        this.height = p.getHeight();
        this.classOf = p.getClass().getSimpleName();
        this.helloMessage = p.getHelloMessage();
        this.birthDate = p.getSimpleBirthDate();
        this.gender = p.getGender().toString();
    }
}
