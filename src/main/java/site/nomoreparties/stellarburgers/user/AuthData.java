package site.nomoreparties.stellarburgers.user;

import org.apache.http.auth.Credentials;

public class AuthData {
    private String email;
    private String password;

    public AuthData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthData() {
    }

    public String getEmail() {
        return email;
    }

    public static AuthData from(User user) {
        return new AuthData(user.getEmail(), user.getPassword());
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
