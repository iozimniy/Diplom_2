package site.nomoreparties.stellarburgers.order;

import java.util.List;

public class Ingredients {
    boolean success;
    private List<Ingredient> data;

    public Ingredients() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Ingredient> getIngredientList() {
        return data;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.data = ingredientList;
    }
}
