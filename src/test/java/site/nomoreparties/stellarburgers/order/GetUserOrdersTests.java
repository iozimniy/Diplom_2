package site.nomoreparties.stellarburgers.order;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.user.*;

public class GetUserOrdersTests {

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

        var order = OrderGenerator.orderWithIngredient();
        ValidatableResponse orderWithIngredient = OrderClient.createOrderWithAuth(order, accessToken);
    }

    @Test
    public void getUsersOrdersWithAuth() {
        ValidatableResponse getUsersOrders = OrderClient.getUsersOrdersWithAuth(accessToken);
        checks.getUsersOrdersSuccessfully(getUsersOrders);
    }

    @Test
    public void getUsersOrdersWithoutAuth() {
        ValidatableResponse getUsersOrders = OrderClient.getUsersOrdersWithoutAuth();
        checks.getUsersOrdersWithoutAuth(getUsersOrders);
    }

    @After
    public void deleteUser() {
        ValidatableResponse deleteUser = UserClient.delete(accessToken);
        UserChecks.assertUserDeleteSuccsessfully(deleteUser);
    }

}
