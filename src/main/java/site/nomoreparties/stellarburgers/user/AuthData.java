package site.nomoreparties.stellarburgers.user;

public class AuthData {
    private String email;
    private String password;

    public AuthData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthData() {
    }

    public static AuthData from(User user) {
        return new AuthData(user.getEmail(), user.getPassword());
    }

    public static AuthData wrongEmail(User user) {
        return new AuthData(DataGenerator.generateRandomEmail(), user.getPassword());
    }

    public static AuthData wrongPassword(User user) {
        return new AuthData(user.getEmail(), DataGenerator.generateRandomPassword());
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
