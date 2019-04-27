package model;

import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<Ingredient> missingIngredients;

    public double calculateMissingIngredientAmount(double restAmount, double requireAmount){
        return (requireAmount-restAmount);
    }

    public void addMissingIngredient(Ingredient missingIngredients){
        this.missingIngredients.add(missingIngredients);
    }
}
