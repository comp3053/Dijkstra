package model;

import java.util.ArrayList;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private ArrayList<RecipeIngredient> ingredients;

    public Recipe(int id, String name, String description) {
        setID(id);
        setName(name);
        setDescription(description);
    }

    public Recipe(String name, String description, ArrayList<RecipeIngredient> ingredients) {
        setName(name);
        setDescription(description);
        setIngredients(ingredients);
    }

    public Recipe(int id, String name, String description, ArrayList<RecipeIngredient> ingredients) {
        setID(id);
        setName(name);
        setDescription(description);
        setIngredients(ingredients);
    }

    public int getID() {
        return id;
    }

    private void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void amountConversion() {
        //TODO
    }

    public void modifyRecipeIngredient(ArrayList<RecipeIngredient> ingredients) { //TODO: Not the same as document
        this.ingredients = ingredients;
    }

    public void addRecipeIngredient(ArrayList<RecipeIngredient> ingredients) { //TODO: Not the same as document
        this.ingredients = ingredients;
    }
}
