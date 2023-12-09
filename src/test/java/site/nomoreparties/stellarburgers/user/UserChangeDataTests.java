package site.nomoreparties.stellarburgers.user;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserChangeDataTests {
    private final UserClient client = new UserClient();
    private final UserChecks check = new UserChecks();
    private final User user = UserGenerator.generateRandomUser();
    private String accessToken;

    @Before
    public void setUpUser() {
        client.createUser(user);

        var authdata = AuthData.from(user);
        ValidatableResponse login = client.loginUser(authdata);

        accessToken = check.accertUserLoginSuccessfully(login, user.getEmail(), user.getName());
    }

    @Test
    public void changeEmailTest() {
        ChangeData changeData = ChangeData.changeEmail(user);
        ValidatableResponse changeUserEmail = client.changeUserData(changeData, accessToken);
        check.assertChangeEmailSuccessfully(changeUserEmail, changeData.getEmail());
    }

    @Test
    public void changeNameTest() {
        ChangeData changeData = ChangeData.changeName(user);
        ValidatableResponse changeUserName = client.changeUserData(changeData, accessToken);
        check.assertChangeNameSuccessfully(changeUserName, changeData.getName());
    }

    @After
    public void deleteUser() {
        ValidatableResponse deleteUser = UserClient.delete(accessToken);
        check.assertUserDeleteSuccsessfully(deleteUser);
    }
}
