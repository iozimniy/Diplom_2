package site.nomoreparties.stellarburgers.user;

import org.junit.Test;

import io.restassured.response.ValidatableResponse;

public class CreateUserTests {

    private final UserClient client = new UserClient();
    private final UserChecks check = new UserChecks();



    @Test
    public void createUserTest() {
        //создание пользователя
        var user = UserGenerator.generateRandomUser();
        ValidatableResponse response = client.createUser(user);
        check.assertUserCreateSuccessfuly(response, user.getEmail(), user.getName());

        //логин пользователя
        var authdata = AuthData.from(user);
        ValidatableResponse validatableResponse = client.loginUser(authdata);
        check.accertUserLoginSuccessfuly(validatableResponse, user.getEmail(), user.getName());
    }

    //@After
    //public void deleteUser() {
    //    client.delete();
    //}
}
