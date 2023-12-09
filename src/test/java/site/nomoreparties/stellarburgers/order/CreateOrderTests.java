package site.nomoreparties.stellarburgers.order;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.user.*;

public class CreateOrderTests {

    private final OrderClient client = new OrderClient();
    private final OrderChecks checks = new OrderChecks();
    private User user;
    private String accessToken;

    @Before
    public void setAndAuthUser() {
        user = UserGenerator.generateRandomUser();
        ValidatableResponse response = UserClient.createUser(user);
        accessToken = UserChecks.assertUserCreateSuccessfully(response, user.getEmail(), user.getName());

        var authdata = AuthData.from(user);
        UserClient.loginUser(authdata);
    }

    @Test
    public void createOrderWithIngredient() {
        var order = OrderGenerate.orderWithIngredient();
        ValidatableResponse orderWithIngredient = client.createOrderWithAuth(order, accessToken);
        checks.createOrderSuccessfully(orderWithIngredient);
    }

    @After
    public void deleteUser() {
        ValidatableResponse deleteUser = UserClient.delete(accessToken);
        UserChecks.assertUserDeleteSuccsessfully(deleteUser);
    }
}
