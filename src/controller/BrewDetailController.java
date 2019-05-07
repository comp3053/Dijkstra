package controller;

import view.BrewReciptView;
import view.RecommendRecipeListView;

public class BrewDetailController {
    public BrewDetailController(){

    }

    public void goBack(){
        // TODO: Check if there are enough ingredient
        RecommendRecipeListController rrlc = new RecommendRecipeListController();
        RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc, false);
        rrlv.setVisible(true);
    }

    public void brewRecipe(){
        BrewReciptController brc = new BrewReciptController();
        BrewReciptView brv = new BrewReciptView(brc);
        brv.setVisible(true);
    }
}
