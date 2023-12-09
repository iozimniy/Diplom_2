package site.nomoreparties.stellarburgers.user;

import org.apache.commons.lang3.RandomStringUtils;

public class DataGenerator {
    public static String email;
    public static String name;

    public static String generateRandomEmail() {
        return "govard" + RandomStringUtils.randomNumeric(4) + "@arkhamworld.com";
    }

    public static String generateRandomName() {
        return "Govard F.L." + RandomStringUtils.randomNumeric(4);
    }
}
