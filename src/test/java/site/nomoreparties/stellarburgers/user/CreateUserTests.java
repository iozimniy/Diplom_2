package site.nomoreparties.stellarburgers.user;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CreateUserTests {

    private final UserClient client = new UserClient();
    private final UserChecks check = new UserChecks();
    private String accessToken;


    @Test
    public void createUserTest() {
        var user = UserGenerator.generateRandomUser();
        ValidatableResponse response = UserClient.createUser(user);
        check.assertUserCreateSuccessfully(response, user.getEmail(), user.getName());

        var authdata = AuthData.from(user);
        ValidatableResponse validatableResponse = UserClient.loginUser(authdata);
        accessToken = check.accertUserLoginSuccessfully(validatableResponse, user.getEmail(), user.getName());
    }

    @After
    public void deleteUser() {
        ValidatableResponse deleteUser = UserClient.delete(accessToken);
        check.assertUserDeleteSuccsessfully(deleteUser);
    }
}
