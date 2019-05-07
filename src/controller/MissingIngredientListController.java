package controller;

import view.HomeView;
import view.MissingIngredientsListView;
import view.RecommendRecipeListView;

public class MissingIngredientListController {
    public MissingIngredientListController(){

    }

    public void readMissingIngredientList(){

    }

    public void goBack(){
        // TODO: Check if there are enough ingredient
        RecommendRecipeListController rrlc = new RecommendRecipeListController();
        RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc, false);
        rrlv.setVisible(true);
    }

    public void OK(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
