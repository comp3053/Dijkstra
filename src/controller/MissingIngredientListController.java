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

    public void OK(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
