package site.nomoreparties.stellarburgers.order;

import io.qameta.allure.Step;

public class IngredientsClient extends site.nomoreparties.stellarburgers.Client {
    static final String INGREDIENTS = "api/ingredients";

    public static Ingredients getIngredients() {
        return spec()
                .when()
                .get(INGREDIENTS)
                .body().as(Ingredients.class);
    }
}
