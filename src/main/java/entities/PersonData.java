package entities;

import com.google.gson.annotations.SerializedName;

/**
 * @author Arthur Kupriyanov
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getClassOf() {
        return classOf;
    }

    public void setClassOf(String classOf) {
        this.classOf = classOf;
    }

    public String getHelloMessage() {
        return helloMessage;
    }

    public void setHelloMessage(String helloMessage) {
        this.helloMessage = helloMessage;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
