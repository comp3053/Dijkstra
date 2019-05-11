package controller;

import model.Equipment;
import model.Recipe;
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
                BrewDetailController bdc = new BrewDetailController(recipe);
                BrewDetailView bdv = new BrewDetailView(bdc,recipe);
                recipe.addListener(bdv);
                bdv.setVisible(true);
                break;
            }
        }

    }

    public void generateShoppingList(ArrayList<Recipe> recommendRecipe, int recipeID){
        MissingIngredientListController milc = new MissingIngredientListController();
        MissingIngredientsListView milv = new MissingIngredientsListView(milc,recommendRecipe.get(recipeID-1));
        milv.setVisible(true);
    }
}
