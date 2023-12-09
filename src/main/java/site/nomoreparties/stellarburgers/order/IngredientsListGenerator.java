package site.nomoreparties.stellarburgers.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IngredientsListGenerator {

    public static List<String> getListIdIngredient() {
        List<Ingredient> ingredientObjects = IngredientsClient.getIngredients().getIngredientList();
        List<String> ingredientsId = new ArrayList<>();
        for (Ingredient ingredientObject : ingredientObjects) {
            ingredientsId.add(ingredientObject.get_id());
        }
        return ingredientsId;
    }

    public static String getRandomIngredient() {
        var random = new Random();
        var list = getListIdIngredient();
        return list.get(random.nextInt(list.size()));
    }

    public static String getWrongIngredient() {
        var random = new Random();
        return getRandomIngredient() + random.nextInt(6);
    }
}
