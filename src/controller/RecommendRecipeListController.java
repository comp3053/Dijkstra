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

    public RecommendRecipeListController(ArrayList<Recipe> recommendRecipes) {//initial the recommend recipe list controller
        this.recommendRecipes = recommendRecipes;
    }

    public void goBack() {// go back to the home view
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void brewRecipe(Recipe recommendRecipe) {// go to the recommend recipe view
        try {
            int equipmentBatchSize = Equipment.getEquipment(1).getVolume();
            recommendRecipe.amountConversion(1000, equipmentBatchSize);
        } catch (FetchDataException | InvalidInputException | EmptyNameException e) {
            e.printStackTrace();
        }
        BrewDetailController bdc = new BrewDetailController(recommendRecipe);
        BrewDetailView bdv = new BrewDetailView(bdc, recommendRecipe);
        // add a listener for update the brew detail view
        recommendRecipe.addListener(bdv);
        bdv.setVisible(true);
    }

    public void generateShoppingList(Recipe notEnoughRecommendRecipe,ArrayList<Recipe> recommendRecipes) {//go to the generate shopping list view
        try {
            ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
            ArrayList<StorageIngredient> storageIngredients = StorageIngredient.getAll();
            int equipment = Equipment.getEquipment(1).getVolume();
            // for loop is used to get the required ingredient and calculate the required amount
            for (RecipeIngredient recipeIngredientItem : notEnoughRecommendRecipe.getIngredients()) {
                for (StorageIngredient storageIngredient : storageIngredients) {
                    if (storageIngredient.getID() == recipeIngredientItem.getID()) {
                        if (storageIngredient.getAmount() < recipeIngredientItem.getAmount()) {
                            recipeIngredientItem.setAmount(recipeIngredientItem.getAmount() / 1000 * equipment - storageIngredient.getAmount());
                            recipeIngredients.add(recipeIngredientItem);
                        }
                    }
                }
            }
            notEnoughRecommendRecipe.setIngredients(recipeIngredients);
            MissingIngredientListController milc = new MissingIngredientListController();
            MissingIngredientsListView milv = new MissingIngredientsListView(milc, notEnoughRecommendRecipe);
            milv.setVisible(true);
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
