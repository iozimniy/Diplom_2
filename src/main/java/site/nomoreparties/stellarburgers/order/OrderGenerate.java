package site.nomoreparties.stellarburgers.order;

import java.util.ArrayList;
import java.util.List;

public class OrderGenerate {

    public static Order orderWithIngredient() {
        return new Order(List.of(IngredientsListGenerator.getRandomIngredient()));
    }

    public static Order orderWithoutIngredients() {
        return new Order(List.of());
    }

    public static Order orderWithWrongIngredients() {
        return new Order(List.of(IngredientsListGenerator.getWrongIngredient()));
    }
}
