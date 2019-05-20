package model;

import java.util.ArrayList;

public class RecipeForm {
    private Recipe recipe;
    private int batchSize;
    private ArrayList<StorageIngredient> storageIngredients;
    private ArrayList<RecipeIngredient> recipeIngredients;

    public RecipeForm(Recipe recipe, ArrayList<StorageIngredient> storageIngredients) {
        this.recipe = recipe;
        this.storageIngredients = storageIngredients;
    }

    /**
     * Insert ingredients into recipe.
     * @param recipeID Recipe you need to insert ingredients into.
     * @return Whether the insert operation is successful.
     */
    private boolean insertIngredients(int recipeID) {
        boolean status = true;
        for (RecipeIngredient ingredient : recipeIngredients) {
            ingredient.setRecipeID(recipeID);
            status &= ingredient.insert();
        }
        return status;
    }

    /**
     * Save the information in the recipe form into Recipe and RecipeIngredients.
     * @return Whether the save operation is successful.
     */
    public boolean save() {
        boolean status;
        if (recipe.getID() == 0)
            status = insert();
        else
            status = update();
        return status;
    }

    /**
     * Insert recipe information into database if recipe not exist.
     * @return Whether the insert operation is successful.
     */
    private boolean insert() {
        recipe.setIngredients(recipeIngredients);
        return recipe.insert();
    }

    /**
     * Update recipe information of database if recipe exists.
     * @return Whether the update operation is successful.
     */
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

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public double getBatchSize() {
        return batchSize;
    }

    public ArrayList<StorageIngredient> getStorageIngredients() {
        return storageIngredients;
    }
}
