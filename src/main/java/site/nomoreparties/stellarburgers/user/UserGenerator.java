package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;

public class UserGenerator {

    @Step("Создание валидных регистрационных данных")
    public static User generateRandomUser() {
        return new User(DataGenerator.generateRandomEmail(), DataGenerator.generateRandomPassword(), DataGenerator.generateRandomName());
    }

    @Step("Создание невалидных регистрационных данных - пустой адрес почты")
    public static User generateRandomWithoutEmail() {
        return new User("", DataGenerator.generateRandomPassword(), DataGenerator.generateRandomName());
    }

    @Step("Создание невалидных регистрационных данных - пустое имя")
    public static User generateRandomWithoutName() {
        return new User(DataGenerator.generateRandomEmail(), DataGenerator.generateRandomPassword(), "");
    }

    @Step("Создание невалидных регистрационных данный - пустой пароль")
    public static User generateRandomWithoutPassword() {
        return new User(DataGenerator.generateRandomEmail(), "", DataGenerator.generateRandomName());
    }
}
