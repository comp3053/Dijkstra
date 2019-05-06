package model;

import utils.UnitEnum;

public class RecipeIngredient extends Ingredient {
    public RecipeIngredient(String name, double amount, UnitEnum unit) throws EmptyIngredientNameException,
            InvalidIngredientAmountException {
        super(name, amount, unit);
    }

    public RecipeIngredient(int id, String name, double amount, UnitEnum unit) throws EmptyIngredientNameException,
            InvalidIngredientAmountException {
        super(id, name, amount, unit);
    }
}
