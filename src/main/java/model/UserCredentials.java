package model;

public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public static UserCredentials from (User user){
        return new UserCredentials(user);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
