package site.nomoreparties.stellarburgers.user;


import io.qameta.allure.Step;

public class ChangeData {
    private String name;
    private String email;

    public ChangeData() {
    }

    public ChangeData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Step("Создание данных для изменения почты")
    public static ChangeData changeEmail(User user) {
        return new ChangeData(user.getName(), DataGenerator.generateRandomEmail());
    }

    @Step("Создание данных для изменение имени")
    public static ChangeData changeName(User user) {
        return new ChangeData(DataGenerator.generateRandomName(), user.getEmail());
    }

    @Step("Создание данных для изменения почты и имени")
    public static ChangeData changeEmailAndName() {
        return new ChangeData(DataGenerator.generateRandomName(), DataGenerator.generateRandomEmail());
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
}
