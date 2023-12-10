package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;

public class AuthData {
    private String email;
    private String password;

    public AuthData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthData() {
    }

    @Step("Создание валидных авторизационных данных")
    public static AuthData from(User user) {
        return new AuthData(user.getEmail(), user.getPassword());
    }

    @Step("Создание невалидных авторизационных данных - неправильная почта")
    public static AuthData wrongEmail(User user) {
        return new AuthData(DataGenerator.generateRandomEmail(), user.getPassword());
    }

    @Step("Создание невалидных авторизационных данных - неправильный пароль")
    public static AuthData wrongPassword(User user) {
        return new AuthData(user.getEmail(), DataGenerator.generateRandomPassword());
    }

    @Step("Создание невалидных авторизационных данных - неправильные почта и пароль")
    public static AuthData wrongEmailAndPassword() {
        return new AuthData(DataGenerator.generateRandomEmail(), DataGenerator.generateRandomPassword());
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
