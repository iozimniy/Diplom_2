package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserChecks {

    @Step("Creare user and Getting accessToken")
    public String assertUserCreateSuccessfully(ValidatableResponse response, String email, String name) {
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
    public String accertUserLoginSuccessfully(ValidatableResponse response, String email, String name) {
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

    public void assertChangeEmailSuccessfully(ValidatableResponse response, String email) {
        ValidatableResponse changeEmail = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email));
    }

    public void assertChangeNameSuccessfully(ValidatableResponse response, String name) {
        ValidatableResponse changeName = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.name", equalTo(name));
    }

    public void assertChangeEmailAndNameSuccessfuly(ValidatableResponse response, String email, String name) {
        ValidatableResponse changeEmailAndName = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name));
    }

    public void assertUserDeleteSuccsessfully(ValidatableResponse response) {
        ValidatableResponse deleteUser = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED);
    }

    public void assertErrorCreateUserBecauseAlreadyCreate(ValidatableResponse response) {
        ValidatableResponse createAgain = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }

    public void assertErrorCreateUserWithoutRequiredField(ValidatableResponse response) {
        ValidatableResponse createWithoutRequiredField = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    public void assertErrorLoginUserWithWrongData(ValidatableResponse response) {
        ValidatableResponse loginWithWrongData = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}
