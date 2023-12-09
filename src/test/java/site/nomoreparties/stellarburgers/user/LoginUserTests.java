package site.nomoreparties.stellarburgers.user;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginUserTests {
    private final UserClient client = new UserClient();
    private final UserChecks check = new UserChecks();
    private User user;
    String accessToken;

    @Before
    public void setUser() {
        user = UserGenerator.generateRandomUser();
        UserClient.createUser(user);
    }

    @Test
    public void loginUserTest() {
        var authdata = AuthData.from(user);
        ValidatableResponse login = client.loginUser(authdata);
        accessToken = check.accertUserLoginSuccessfully(login, user.getEmail(), user.getName());
    }

    @Test
    public void errorLoginWithWrongEmail() {
        var authdata = AuthData.wrongEmail(user);
        ValidatableResponse loginWithWrongEmail = client.loginUser(authdata);
        check.assertErrorLoginUserWithWrongData(loginWithWrongEmail);
    }

    @Test
    public void errorLoginWithWrongPassword() {
        var authdata = AuthData.wrongPassword(user);
        ValidatableResponse loginWithWrongPassword = client.loginUser(authdata);
        check.assertErrorLoginUserWithWrongData(loginWithWrongPassword);
    }

    @Test
    public void errorLoginWithWrongEmailAndPassword() {
        var authdata = AuthData.wrongEmailAndPassword();
        ValidatableResponse loginWithWrongEmailAndPassword = client.loginUser(authdata);
        check.assertErrorLoginUserWithWrongData(loginWithWrongEmailAndPassword);
    }

    @After
    public void deleteUser() {
        if(accessToken != null) {
            ValidatableResponse deleteUser = client.delete(accessToken);
            check.assertUserDeleteSuccsessfully(deleteUser);
        }
    }
}