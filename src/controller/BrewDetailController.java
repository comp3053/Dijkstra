package controller;

import model.Recipe;
import utils.FetchDataException;
import view.BrewReciptView;
import view.RecommendRecipeListView;

import java.util.ArrayList;

public class BrewDetailController {
    public BrewDetailController(){

    }

    public void goBack(){
        // TODO: Check if there are enough ingredient
        RecommendRecipeListController rrlc = new RecommendRecipeListController();
        RecipeController rc = new RecipeController();
        try {
            ArrayList<Recipe> recommendRecipe = rc.getAll();
            RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc,recommendRecipe, false);
            rrlv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }

    public void brewRecipe(){
        BrewReciptController brc = new BrewReciptController();
        BrewReciptView brv = new BrewReciptView(brc);
        brv.setVisible(true);
    }
}
