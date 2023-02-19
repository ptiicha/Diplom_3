package model;
import org.apache.commons.lang3.RandomStringUtils;

public class User {

    private String email;
    private String name;
    private String password;

    public User(String email, String name, String password) {
    }

    public User(String name, String email) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }
}