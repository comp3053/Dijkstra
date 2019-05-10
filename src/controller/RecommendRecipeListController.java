package controller;

import model.Recipe;
import view.BrewDetailView;
import view.HomeView;
import view.MissingIngredientsListView;

import java.util.ArrayList;

public class RecommendRecipeListController {
    public RecommendRecipeListController(){

    }

    public void goBack(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void brewRecipe(ArrayList<Recipe> recommendRecipe, int recipeID){
        BrewDetailController bdc = new BrewDetailController();
        BrewDetailView bdv = new BrewDetailView(bdc,recommendRecipe.get(recipeID-1));
        bdv.setVisible(true);
    }

    public void generateShoppingList(ArrayList<Recipe> recommendRecipe, int recipeID){
        MissingIngredientListController milc = new MissingIngredientListController();
        MissingIngredientsListView milv = new MissingIngredientsListView(milc,recommendRecipe.get(recipeID-1));
        milv.setVisible(true);
    }
}
