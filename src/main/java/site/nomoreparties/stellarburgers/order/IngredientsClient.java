package site.nomoreparties.stellarburgers.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;

public class IngredientsClient extends site.nomoreparties.stellarburgers.Client {
    static final String INGREDIENTS = "api/ingredients";

    @Step("Get Ingredients")
    public static Ingredients getIngredients() {
         return spec()
                .when()
                .get(INGREDIENTS)
                .body().as(Ingredients.class);
    }
}
