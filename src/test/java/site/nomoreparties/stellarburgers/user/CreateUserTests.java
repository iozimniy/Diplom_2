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
        ValidatableResponse response = client.createUser(user);
        accessToken = check.assertUserCreateSuccessfully(response, user.getEmail(), user.getName());
    }

    @Test
    public void errorCreateUserBecauseAlreadyCreate() {
        var user = UserGenerator.generateRandomUser();
        ValidatableResponse response = client.createUser(user);
        accessToken = check.assertUserCreateSuccessfully(response, user.getEmail(), user.getName());
        ValidatableResponse createAgain = client.createUser(user);
        check.assertErrorCreateUserBecauseAlreadyCreate(createAgain);

    }

    @Test
    public void errorCreateWithoutEmail() {
        var user = UserGenerator.generateRandomWithoutEmail();
        ValidatableResponse createWithoutEmail = client.createUser(user);
        check.assertErrorCreateUserWithoutRequiredField(createWithoutEmail);
    }

    @Test
    public void errorCreateWithoutName() {
        var user = UserGenerator.generateRandomWithoutName();
        ValidatableResponse createWithoutName = client.createUser(user);
        check.assertErrorCreateUserWithoutRequiredField(createWithoutName);
    }

    @Test
    public void errorCreateWithoutPassword() {
        var user = UserGenerator.generateRandomWithoutPassword();
        ValidatableResponse createWithoutPassword = client.createUser(user);
        check.assertErrorCreateUserWithoutRequiredField(createWithoutPassword);
    }

    @After
    public void deleteUser() {
        if(accessToken != null) {
            ValidatableResponse deleteUser = client.delete(accessToken);
            check.assertUserDeleteSuccsessfully(deleteUser);
        }
    }
}
