package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;
import org.junit.Test;

import io.restassured.response.ValidatableResponse;

public class LoginUserTests {
    private final UserClient client = new UserClient();
    private final UserChecks check = new UserChecks();

    @Test
    public void loginUserTest() {

        var user = UserGenerator.generateRandomUser();
        ValidatableResponse create = client.createUser(user);

        var authdata = AuthData.from(user);
        ValidatableResponse login = client.loginUser(authdata);
        check.accertUserLoginSuccessfuly(login, user.getEmail(), user.getName());
    }
}
