package model;

import utils.DatabaseHelper;

import java.util.ArrayList;

public class RecipeForm {
    private Recipe recipe;
    private ArrayList<StorageIngredient> storageIngredients;
    private ArrayList<RecipeIngredient> recipeIngredients;

    public RecipeForm (Recipe recipe, ArrayList<StorageIngredient> storageIngredients) {
        this.recipe = recipe;
        this.storageIngredients = storageIngredients;
    }

    public boolean insertIngredients(int recipeID) {
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

    public boolean insert() {
        recipe.setIngredients(recipeIngredients);
        return recipe.insert();
    }

    public boolean update() {
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

    public ArrayList<StorageIngredient> getStorageIngredients() {
        return storageIngredients;
    }
}
