package controller;

import model.Recipe;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
import view.BrewDetailView;
import view.BrewReciptView;
import view.RecommendRecipeListView;

import java.util.ArrayList;

public class BrewDetailController {
    public BrewDetailController(){

    }

    public void goBack(){
        // TODO: Check if there are enough ingredient
        RecommendRecipeListController rrlc = new RecommendRecipeListController();
        try {
            ArrayList<Recipe> recommendRecipe = new Recipe().getAll();
            RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc,recommendRecipe, false);
            rrlv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        } catch (EmptyNameException e) {
            e.printStackTrace();
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    public void applyBatchSize(double batchSize,Recipe recipe){//TODO:fixing the mathematical bug
        try {
            recipe.amountConversion(batchSize);
            BrewDetailController bdc= new BrewDetailController();
            BrewDetailView bdv = new BrewDetailView(bdc,recipe);
            bdv.setVisible(true);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    public void brewRecipe(Recipe recipe,double batchSize){
        BrewReciptController brc = new BrewReciptController();
        BrewReciptView brv = new BrewReciptView(brc,recipe,batchSize);
        brv.setVisible(true);
    }
}
