package controller;

import view.HomeView;

public class IngredientListController {
    public IngredientListController(){

    }

    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
