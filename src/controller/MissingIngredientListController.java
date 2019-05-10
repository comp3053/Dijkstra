package controller;

import model.Recipe;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
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
        RecommendRecipeListController rrlc = new RecommendRecipeListController();
        try {
            ArrayList<Recipe> recommendRecipe =  new Recipe().getAll();
            boolean viewStatus = true;
            for (Recipe recipe : recommendRecipe) {
                if (recipe.isAvailable()) {
                    viewStatus = false;
                    break;
                }
            }
            RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc,recommendRecipe, viewStatus);
            rrlv.setVisible(true);
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

    public void OK(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
