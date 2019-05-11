package controller;

import model.*;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
import view.BrewDetailView;
import view.HomeView;
import view.MissingIngredientsListView;

import java.util.ArrayList;

public class RecommendRecipeListController {
    private ArrayList<Recipe> recommendRecipes;
    public RecommendRecipeListController(ArrayList<Recipe> recommendRecipes){
        this.recommendRecipes= recommendRecipes;
    }

    public void goBack(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void brewRecipe(ArrayList<Recipe> recommendRecipe, int recipeID){
        for(Recipe recipe: recommendRecipe){
            if (recipe.getID() == recipeID){
                try {
                    int equipmentBatchSize=Equipment.getEquipment(1).getVolume();
                    recipe.amountConversion(1000,equipmentBatchSize);
                } catch (FetchDataException | InvalidInputException | EmptyNameException e) {
                    e.printStackTrace();
                }
                BrewDetailController bdc = new BrewDetailController(recipe);
                BrewDetailView bdv = new BrewDetailView(bdc,recipe);
                recipe.addListener(bdv);
                bdv.setVisible(true);
                break;
            }
        }

    }

    public void generateShoppingList(ArrayList<Recipe> recommendRecipe, int recipeID){
        try {
            Recipe recipe = recommendRecipe.get(recipeID-1);
            ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
            ArrayList<StorageIngredient> storageIngredients = StorageIngredient.getAll();
            for(int i =0;i<recipe.getIngredients().size();i++) {
                for (StorageIngredient storageIngredient : storageIngredients) {
                    if (storageIngredient.getID() == recipe.getIngredients().get(i).getID()) {
                        if (storageIngredient.getAmount() < recipe.getIngredients().get(i).getAmount()) {
                            recipe.getIngredients().get(i).setAmount(recipe.getIngredients().get(i).getAmount() - storageIngredient.getAmount());
                            recipeIngredients.add(recipe.getIngredients().get(i));
                        }
                    }
                }
            }
            recipe.setIngredients(recipeIngredients);
            MissingIngredientListController milc = new MissingIngredientListController();
            MissingIngredientsListView milv = new MissingIngredientsListView(milc,recipe);
            milv.setVisible(true);
        } catch (FetchDataException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
