package controller;

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
        System.out.println("Brew This Recipe");
    }
}
