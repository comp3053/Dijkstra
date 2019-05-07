package controller;

import model.Recipe;
import utils.FetchDataException;
import view.HomeView;
import view.MissingIngredientsListView;
import view.RecommendRecipeListView;

import java.util.ArrayList;

public class MissingIngredientListController {
    public MissingIngredientListController(){

    }

    public void readMissingIngredientList(){

    }

    public void goBack(){
        // TODO: Check if there are enough ingredient
        RecommendRecipeListController rrlc = new RecommendRecipeListController();
        RecipeController rc = new RecipeController();
        try {
            ArrayList<Recipe> recommendRecipe = rc.getAll();
            RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc, recommendRecipe, false);
            rrlv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }

    public void OK(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
