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
        accessToken = UserChecks.assertUserCreateSuccessfully(response, user.getEmail(), user.getName());
    }

    @Test
    public void errorCreateUserBecauseAlreadyCreate() {
        var user = UserGenerator.generateRandomUser();
        ValidatableResponse response = UserClient.createUser(user);
        accessToken = UserChecks.assertUserCreateSuccessfully(response, user.getEmail(), user.getName());
        ValidatableResponse createAgain = UserClient.createUser(user);
        check.assertErrorCreateUserBecauseAlreadyCreate(createAgain);

    }

    @Test
    public void errorCreateWithoutEmail() {
        var user = UserGenerator.generateRandomWithoutEmail();
        ValidatableResponse createWithoutEmail = UserClient.createUser(user);
        check.assertErrorCreateUserWithoutRequiredField(createWithoutEmail);
    }

    @Test
    public void errorCreateWithoutName() {
        var user = UserGenerator.generateRandomWithoutName();
        ValidatableResponse createWithoutName = UserClient.createUser(user);
        check.assertErrorCreateUserWithoutRequiredField(createWithoutName);
    }

    @Test
    public void errorCreateWithoutPassword() {
        var user = UserGenerator.generateRandomWithoutPassword();
        ValidatableResponse createWithoutPassword = UserClient.createUser(user);
        check.assertErrorCreateUserWithoutRequiredField(createWithoutPassword);
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            ValidatableResponse deleteUser = UserClient.delete(accessToken);
            UserChecks.assertUserDeleteSuccsessfully(deleteUser);
        }
    }
}
