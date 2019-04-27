package model;

import utils.UnitEnum;

public class RecipeIngredient extends Ingredient {
    public RecipeIngredient(int id, String name, double amount, UnitEnum unit) throws EmptyIngredientNameException,
            InvalidIngredientAmountException {
        super(id, name, amount, unit);
    }
}
