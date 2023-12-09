package site.nomoreparties.stellarburgers.user;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class LoginUserTests {
    private final UserClient client = new UserClient();
    private final UserChecks check = new UserChecks();
    String accessToken;

    @Test
    public void loginUserTest() {

        var user = UserGenerator.generateRandomUser();
        UserClient.createUser(user);

        var authdata = AuthData.from(user);
        ValidatableResponse login = client.loginUser(authdata);
        accessToken = check.accertUserLoginSuccessfully(login, user.getEmail(), user.getName());
    }

    @After
    public void deleteUser() {
        ValidatableResponse deleteUser = client.delete(accessToken);
        check.assertUserDeleteSuccsessfully(deleteUser);
    }
}
