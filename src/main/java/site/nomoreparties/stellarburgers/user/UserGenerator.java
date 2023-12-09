package site.nomoreparties.stellarburgers.user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User generateUser() {
        return new User("govard@arkhamworld.com", "YAYA#yogsotot", "Govard F.L.");
    }

    public static User generateRandomUser() {
        return new User("govard" + RandomStringUtils.randomNumeric(4) + "@arkhamworld.com", "YAYA#yogsotot", "Govard F.L." + RandomStringUtils.randomNumeric(4));
    }

    public static User generateRandomWithoutEmail() {
        return new User("", "YAYA#yogsotot", "Govard F.L." + RandomStringUtils.randomNumeric(4));
    }

    public static User generateRandomWithoutName() {
        return new User("govard" + RandomStringUtils.randomNumeric(4) + "@arkhamworld.com", "YAYA#yogsotot", "");
    }

    public static User generateRandomWithoutPassword() {
        return new User("govard" + RandomStringUtils.randomNumeric(4) + "@arkhamworld.com", "", "Govard F.L." + RandomStringUtils.randomNumeric(4));
    }
}
