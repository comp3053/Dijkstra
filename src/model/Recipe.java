package model;

import utils.AddObjectException;
import utils.InvalidInputException;
import utils.ModifyObjectException;

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

    public void setID(int id) {
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

    public void amountConversion(double originalBatchSize) throws InvalidInputException {//originalBatchSize should be used L as unit.This method is to convert all recipeIngredients to the 1L amount.
        if(originalBatchSize<=0){
            throw new InvalidInputException("Batch size could not be equal or less than 0!");
        }
        else{
            for (RecipeIngredient ingredient : this.ingredients) {
                try {
                    ingredient.setAmount(ingredient.getAmount()* ( originalBatchSize/1000));
                } catch (InvalidInputException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void modifyRecipeIngredient(RecipeIngredient recipeIngredient) throws ModifyObjectException {
        for(int i = 0;i < this.ingredients.size();i++){
            if(this.ingredients.get(i).getName().equals(recipeIngredient.getName())){
                this.ingredients.remove(i);
                this.ingredients.add(i, recipeIngredient);
                return;
            }
        }
        throw new ModifyObjectException("Cannot modify a recipe not existing!");
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredients) throws AddObjectException {
        for (RecipeIngredient ingredient : this.ingredients) {
            if (ingredient == recipeIngredients) {
                throw new AddObjectException(recipeIngredients.getName() + "is already existed!");
            }
        }
        this.ingredients.add(recipeIngredients);
    }
}
