package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class UserClient extends site.nomoreparties.stellarburgers.Client {
    static final String USER_PATH_CREATE = "api/auth/register";
    static final String USER_PATH_LOGIN = "api/auth/login";
    static final String USER_PATH_DATA = "api/auth/user";

    @Step("Создание пользователя")
    public static ValidatableResponse createUser(User user) {
        return spec()
                .body(user)
                .when()
                .post(USER_PATH_CREATE)
                .then().log().all();
    }

    @Step("Авторизация пользователя")
    public static ValidatableResponse loginUser(AuthData authdata) {
        return spec()
                .body(authdata)
                .when()
                .post(USER_PATH_LOGIN)
                .then().log().all();
    }

    @Step("Изменяем данные пользователя")
    public static ValidatableResponse changeUserData(ChangeData changeData, String accessToken) {
        return specAuth(accessToken)
                .body(changeData)
                .when()
                .patch(USER_PATH_DATA)
                .then().log().all();
    }

    @Step("Меняем данные пользователя без авторизации")
    public static ValidatableResponse changeUserDataWithoutAuth(ChangeData changeData) {
        return spec()
                .body(changeData)
                .when()
                .patch(USER_PATH_DATA)
                .then().log().all();
    }

    @Step("Удаляем тестового пользователя")
    public static ValidatableResponse delete(String accessToken) {
        return specAuth(accessToken)
                .when()
                .delete(USER_PATH_DATA)
                .then().log().all();
    }
}
