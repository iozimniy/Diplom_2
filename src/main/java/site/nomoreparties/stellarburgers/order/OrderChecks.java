package site.nomoreparties.stellarburgers.order;

import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OrderChecks {

    public void createOrderSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true));
    }

    public void createOrderWithoutIngredients(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .and()
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    public void createOrderWithWrongIngredient(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }

    public void createOrderWithoutAuth(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("order.number", notNullValue());
    }

    public void getUsersOrdersSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true));
    }

    public void getUsersOrdersWithoutAuth(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}
