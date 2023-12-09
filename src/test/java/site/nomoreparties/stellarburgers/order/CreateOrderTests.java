package site.nomoreparties.stellarburgers.order;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.user.*;

public class CreateOrderTests {

    private final OrderClient client = new OrderClient();
    private final OrderChecks checks = new OrderChecks();
    private String accessToken;

    @Before
    public void setAndAuthUser() {
        User user = UserGenerator.generateRandomUser();
        ValidatableResponse response = UserClient.createUser(user);
        accessToken = UserChecks.assertUserCreateSuccessfully(response, user.getEmail(), user.getName());

        var authdata = AuthData.from(user);
        UserClient.loginUser(authdata);
    }

    @Test
    public void createOrderWithIngredient() {
        var order = OrderGenerator.orderWithIngredient();
        ValidatableResponse orderWithIngredient = OrderClient.createOrderWithAuth(order, accessToken);
        checks.createOrderSuccessfully(orderWithIngredient);
    }

    @Test
    public void errorCreateOrderWithoutIngredients() {
        var order = OrderGenerator.orderWithoutIngredients();
        ValidatableResponse orderWithoutIngredients = OrderClient.createOrderWithAuth(order, accessToken);
        checks.createOrderWithoutIngredients(orderWithoutIngredients);
    }

    @Test
    public void errorCreateOrderWithWrongIngredient() {
        var order = OrderGenerator.orderWithWrongIngredient();
        ValidatableResponse orderWithWrongIngredient = OrderClient.createOrderWithAuth(order, accessToken);
        checks.createOrderWithWrongIngredient(orderWithWrongIngredient);
    }

    @Test
    public void createOrderWithoutAuth() {
        var order = OrderGenerator.orderWithIngredient();
        ValidatableResponse orderWithOutAuth = OrderClient.createOrderWithoutAuth(order);
        checks.createOrderWithoutAuth(orderWithOutAuth);
    }

    @After
    public void deleteUser() {
        ValidatableResponse deleteUser = UserClient.delete(accessToken);
        UserChecks.assertUserDeleteSuccsessfully(deleteUser);
    }
}
