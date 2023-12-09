package site.nomoreparties.stellarburgers.user;

import org.junit.Test;

import io.restassured.response.ValidatableResponse;

public class CreateUserTests {

    private final UserClient client = new UserClient();
    private final UserChecks check = new UserChecks();



    @Test
    public void createUserTest() {
        var user = UserGenerator.generateRandomUser();
        ValidatableResponse response = client.createUser(user);
        check.assertUserCreateSuccessfully(response, user.getEmail(), user.getName());

        var authdata = AuthData.from(user);
        ValidatableResponse validatableResponse = client.loginUser(authdata);
        check.accertUserLoginSuccessfully(validatableResponse, user.getEmail(), user.getName());
    }
}
