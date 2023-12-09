package site.nomoreparties.stellarburgers.order;

import java.util.List;

public class OrderGenerator {

    public static Order orderWithIngredient() {
        return new Order(List.of(IngredientsListGenerator.getRandomIngredient()));
    }

    public static Order orderWithoutIngredients() {
        return new Order(List.of());
    }

    public static Order orderWithWrongIngredient() {
        return new Order(List.of(IngredientsListGenerator.getWrongIngredient()));
    }
}
