package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserChecks {

    @Step("Creare user and Getting accessToken")
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

    @Step("Assert Login and Getting accessToken")
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

    public static void assertUserDeleteSuccsessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED);
    }

    public void assertChangeEmailSuccessfully(ValidatableResponse response, String email) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email));
    }

    public void assertChangeNameSuccessfully(ValidatableResponse response, String name) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.name", equalTo(name));
    }

    public void assertChangeEmailAndNameSuccessfuly(ValidatableResponse response, String email, String name) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name));
    }

    public void assertErrorCreateUserBecauseAlreadyCreate(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }

    public void assertErrorCreateUserWithoutRequiredField(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    public void assertErrorLoginUserWithWrongData(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    public void assertErrorChangeDataWithoutAuth(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}
