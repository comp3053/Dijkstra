package controller;

import model.BrewingRecord;
import model.Recipe;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
import view.BrewReciptView;
import view.RecommendRecipeListView;

import java.util.ArrayList;
import java.util.Date;

public class BrewDetailController {
    private Recipe m;
    public BrewDetailController(Recipe m){
        this.m = m;
    }

    public void goBack(){
        try {
            ArrayList<Recipe> recommendRecipe =  new Recipe().getAll();
            ArrayList<Integer> notAvailableList = new ArrayList();
            RecommendRecipeListController rrlc = new RecommendRecipeListController(recommendRecipe);
            boolean viewStatus = true;
            for (Recipe recipe : recommendRecipe) {
                if (recipe.isAvailable()) {
                    viewStatus = false;
                }
                else{
                    //recommendRecipe.remove(recipe);
                    notAvailableList.add(recommendRecipe.indexOf(recipe));
                }
            }
            if (!viewStatus){
                for(Integer index: notAvailableList){
                    recommendRecipe.remove(index.intValue());
                }
            }
            RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc,recommendRecipe, viewStatus);
            rrlv.setVisible(true);
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

    public double applyBatchSize(double originalBatchSize, double targetBatchSize){
        try {
            m.amountConversion(originalBatchSize, targetBatchSize);
            return targetBatchSize;
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        return originalBatchSize;
    }

    public void brewRecipe(double batchSize){
        BrewReciptController brc = new BrewReciptController();
        BrewReciptView brv = new BrewReciptView(brc, m, batchSize);
        brv.setVisible(true);
    }
}
