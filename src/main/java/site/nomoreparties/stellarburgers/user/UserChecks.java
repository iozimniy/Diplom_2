package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserChecks {

    public void assertUserCreateSuccessfully(ValidatableResponse response, String email, String name) {
        ValidatableResponse created = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.email", equalTo(email))
                .body("user.name", equalTo(name))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
        ;
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
                .body("user.email", equalTo(email))
        ;
    }

    public void assertChangeNameSuccessfully(ValidatableResponse response, String name) {
        ValidatableResponse changeEmail = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("user.name", equalTo(name))
                ;
    }
}
