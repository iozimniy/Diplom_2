package site.nomoreparties.stellarburgers.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderClient extends site.nomoreparties.stellarburgers.Client {

    static final String ORDER = "api/orders";

    @Step("Создание заказа авторизованным пользователем")
    public static ValidatableResponse createOrderWithAuth(Order order, String accessToken) {
        return specAuth(accessToken)
                .body(order)
                .when()
                .post(ORDER)
                .then().log().all();
    }

    @Step("Создание заказа неавторизованным пользователем")
    public static ValidatableResponse createOrderWithoutAuth(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER)
                .then().log().all();
    }

    @Step("Получение заказов авторизованного пользователя")
    public static ValidatableResponse getUsersOrdersWithAuth(String accessToken) {
        return specAuth(accessToken)
                .when()
                .get(ORDER)
                .then().log().all();
    }

    @Step("Получение заказазов пользователя без авторизации")
    public static ValidatableResponse getUsersOrdersWithoutAuth() {
        return spec()
                .when()
                .get(ORDER)
                .then().log().all();
    }
}
