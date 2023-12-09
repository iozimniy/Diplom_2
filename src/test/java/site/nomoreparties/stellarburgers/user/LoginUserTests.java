package site.nomoreparties.stellarburgers.user;

import org.junit.Test;

import io.restassured.response.ValidatableResponse;

public class LoginUserTests {
    private final UserClient client = new UserClient();
    private final UserChecks check = new UserChecks();

    @Test
    public void loginUserTest() {

        var user = UserGenerator.generateRandomUser();
        client.createUser(user);

        var authdata = AuthData.from(user);
        ValidatableResponse login = client.loginUser(authdata);
        check.accertUserLoginSuccessfully(login, user.getEmail(), user.getName());
    }
}
