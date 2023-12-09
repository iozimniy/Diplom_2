package site.nomoreparties.stellarburgers.user;

public class UserGenerator {

    public static User generateRandomUser() {
        return new User(DataGenerator.generateRandomEmail(), DataGenerator.generateRandomPassword(), DataGenerator.generateRandomName());
    }

    public static User generateRandomWithoutEmail() {
        return new User("", DataGenerator.generateRandomPassword(), DataGenerator.generateRandomName());
    }

    public static User generateRandomWithoutName() {
        return new User(DataGenerator.generateRandomEmail(), DataGenerator.generateRandomPassword(), "");
    }

    public static User generateRandomWithoutPassword() {
        return new User(DataGenerator.generateRandomEmail(), "", DataGenerator.generateRandomName());
    }
}
