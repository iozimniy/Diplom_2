package site.nomoreparties.stellarburgers.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OrderChecks {

    @Step("Проверяем, что заказ создался")
    public void createOrderSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true));
    }

    @Step("Проверяем, что заказ не создался")
    public void createOrderWithoutIngredients(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .and()
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Step("Проверяем, что статус код 500")
    public void createOrderWithWrongIngredient(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }

    @Step("Проверяем, что без авторизации заказ создаётся")
    public void createOrderWithoutAuth(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true))
                .body("order.number", notNullValue());
    }

    @Step("Проверяем, что авторизованному пользователю возвращается список заказов")
    public void getUsersOrdersSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .and()
                .body("success", equalTo(true));
    }

    @Step("Проверяем, что без авторизации не возвращается список заказов")
    public void getUsersOrdersWithoutAuth(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}
