package model;

import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<Ingredient> missingIngredients;

    public ShoppingList(ArrayList<Ingredient> missingIngredients) {
        this.missingIngredients = missingIngredients;
    }

    public ArrayList<Ingredient> getMissingIngredients() {
        return this.missingIngredients;
    }

    public double calculateMissingIngredientAmount(double restAmount, double requireAmount) {
        return (requireAmount - restAmount);
    }

    public void addMissingIngredient(Ingredient missingIngredients) {
        this.missingIngredients.add(missingIngredients);
    }
}
