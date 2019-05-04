package controller;

import view.BrewDetailView;
import view.HomeView;

public class RecommendRecipeListController {
    public RecommendRecipeListController(){

    }

    public void goBack(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void brewRecipe(){
        BrewDetailController bdc = new BrewDetailController();
        BrewDetailView bdv = new BrewDetailView(bdc);
        bdv.setVisible(true);
    }
}
