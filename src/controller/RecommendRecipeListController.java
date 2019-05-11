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

    public RecommendRecipeListController(ArrayList<Recipe> recommendRecipes) {
        this.recommendRecipes = recommendRecipes;
    }

    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void brewRecipe(Recipe recommendRecipe) {
        try {
            int equipmentBatchSize = Equipment.getEquipment(1).getVolume();
            recommendRecipe.amountConversion(1000, equipmentBatchSize);
        } catch (FetchDataException | InvalidInputException | EmptyNameException e) {
            e.printStackTrace();
        }
        BrewDetailController bdc = new BrewDetailController(recommendRecipe);
        BrewDetailView bdv = new BrewDetailView(bdc, recommendRecipe);
        recommendRecipe.addListener(bdv);
        bdv.setVisible(true);
    }

    public void generateShoppingList(Recipe notEnoughRecommendRecipe) {
        try {
            ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
            ArrayList<StorageIngredient> storageIngredients = StorageIngredient.getAll();
            int equipment = Equipment.getEquipment(1).getVolume();
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
