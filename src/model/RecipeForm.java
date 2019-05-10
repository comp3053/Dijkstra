package model;

import utils.InvalidInputException;

import java.util.ArrayList;

public class RecipeForm {
    private Recipe recipe;
    private double batchSize;
    private ArrayList<StorageIngredient> storageIngredients;
    private ArrayList<RecipeIngredient> recipeIngredients;

    public RecipeForm (Recipe recipe, ArrayList<StorageIngredient> storageIngredients) {
        this.recipe = recipe;
        this.storageIngredients = storageIngredients;
    }

    private boolean insertIngredients(int recipeID) {
        boolean status = true;
        for (RecipeIngredient ingredient : recipeIngredients) {
            ingredient.setRecipeID(recipeID);
            status &= ingredient.insert();
        }
        return status;
    }

    public boolean save() {
        boolean status;
        if (recipe.getID() == 0)
            status = insert();
        else
            status = update();
        return status;
    }

    private boolean insert() {
        recipe.setIngredients(recipeIngredients);
        try {
            recipe.amountConversion(batchSize, 1000);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return false;
        }
        return recipe.insert();
    }

    private boolean update() {
        boolean status = true;
        status &= RecipeIngredient.deleteAll(recipe.getID());
        status &= recipe.update();
        status &= insertIngredients(recipe.getID());
        return status;
    }

    public void setRecipeIngredients(ArrayList<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public ArrayList<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setBatchSize(double batchSize) {
        this.batchSize = batchSize;
    }

    public double getBatchSize() {
        return batchSize;
    }

    public ArrayList<StorageIngredient> getStorageIngredients() {
        return storageIngredients;
    }
}
