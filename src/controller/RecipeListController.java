package controller;

import view.HomeView;

public class RecipeListController {
    public RecipeListController(){

    }

    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
