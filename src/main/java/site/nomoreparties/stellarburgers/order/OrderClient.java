package site.nomoreparties.stellarburgers.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderClient extends site.nomoreparties.stellarburgers.Client {

    static final String ORDER = "api/orders";

    @Step("Creare order with auth")
    public static ValidatableResponse createOrderWithAuth(Order order, String accessToken) {
        return specAuth(accessToken)
                .body(order)
                .when()
                .post(ORDER)
                .then().log().all();
    }

    @Step("Create order without auth")
    public static ValidatableResponse createOrderWithoutAuth(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER)
                .then().log().all();
    }

}
