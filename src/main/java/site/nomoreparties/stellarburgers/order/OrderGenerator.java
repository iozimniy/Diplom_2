package site.nomoreparties.stellarburgers.order;

import io.qameta.allure.Step;

import java.util.List;

public class OrderGenerator {

    @Step("Создание валидного списка ингредиентов")
    public static Order orderWithIngredient() {
        return new Order(List.of(IngredientsListGenerator.getRandomIngredient()));
    }

    @Step("Создание пустого списка ингредиентов")
    public static Order orderWithoutIngredients() {
        return new Order(List.of());
    }

    @Step("Создание списка ингредиентов с неправильным хешем")
    public static Order orderWithWrongIngredient() {
        return new Order(List.of(IngredientsListGenerator.getWrongIngredient()));
    }
}
