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

    public void amountConversion(double originalBatchSize) throws InvalidOriginalBatchSizeException{//originalBatchSize should be used L as unit.This method is to convert all recipeIngredients to the 1L amount.
        if(originalBatchSize<=0){
            throw new InvalidOriginalBatchSizeException("Batch size could not be equal or less than 0!");
        }
        else{
            for(int i = 0;i < this.ingredients.size();i++){
                try {
                    this.ingredients.get(i).setAmount(this.ingredients.get(i).getAmount()/originalBatchSize);
                } catch (InvalidIngredientAmountException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void modifyRecipeIngredient(RecipeIngredient recipeIngredient) throws ModifyNotExisitingRecipeIngredientException {
        for(int i = 0;i < this.ingredients.size();i++){
            if(this.ingredients.get(i).getName().equals(recipeIngredient.getName())==true){
                this.ingredients.remove(i);
                this.ingredients.add(i, recipeIngredient);
                return;
            }
        }
        throw new ModifyNotExisitingRecipeIngredientException("Cannot modify a recipe not existing!");
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredients) throws AddExisitingRecipeIngredientsException{
        for(int i = 0; i < this.ingredients.size();i++){
            if(this.ingredients.get(i)==recipeIngredients) {
                throw new AddExisitingRecipeIngredientsException(recipeIngredients.getName()+"is already existed!");
            }
        }
        this.ingredients.add(recipeIngredients);
    }
}
