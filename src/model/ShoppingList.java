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

    /**
     * Calculate the amount of missing ingredients you need to buy.
     * @param restAmount Rest amount of storage ingredient in database.
     * @param requireAmount Amount of ingredient you need for a brew.
     * @return The amount of ingredient you need to buy.
     */
    public double calculateMissingIngredientAmount(double restAmount, double requireAmount) {
        return (requireAmount - restAmount);
    }

    /**
     * Add missing ingredient into Shopping list.
     * @param missingIngredients Ingredient you need to buy.
     */
    public void addMissingIngredient(Ingredient missingIngredients) {
        this.missingIngredients.add(missingIngredients);
    }
}
