package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserChecks {

    @Step("Проверяем, что пользователь создался успешно")
    public static String assertUserCreateSuccessfully(ValidatableResponse response, String email, String name) {
        String accessToken = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .extract()
                .path("accessToken");
        return accessToken;
    }

    @Step("Проверяем, что пользователь успешно авторизован")
    public static String accertUserLoginSuccessfully(ValidatableResponse response, String email, String name) {
        String accessToken = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .extract()
                .path("accessToken");
        return accessToken;
    }

    @Step("Проверяем, что пользователь успешно удалён")
    public static void assertUserDeleteSuccsessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED);
    }

    @Step("Проверяем, что почта успешно изменена")
    public void assertChangeEmailSuccessfully(ValidatableResponse response, String email) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email));
    }

    @Step("Проверяем, что имя успешно изменено")
    public void assertChangeNameSuccessfully(ValidatableResponse response, String name) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.name", equalTo(name));
    }

    @Step("Проверяем, что почта и имя успешно изменены")
    public void assertChangeEmailAndNameSuccessfuly(ValidatableResponse response, String email, String name) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name));
    }

    @Step("Проверяем, что нельзя создать пользователя, который уже зарегистрирован")
    public void assertErrorCreateUserBecauseAlreadyCreate(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }

    @Step("Проверяем, что нельзя создать пользователя без обязательных данных")
    public void assertErrorCreateUserWithoutRequiredField(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Step("Проверяем, что нельзя залогиниться с неверными данными")
    public void assertErrorLoginUserWithWrongData(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Step("Проверяем, что нельзя изменить данные без авторизации")
    public void assertErrorChangeDataWithoutAuth(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}
