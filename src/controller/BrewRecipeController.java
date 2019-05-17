package controller;

import view.HomeView;

public class BrewRecipeController {
    public BrewRecipeController() {

    }

    public void finish() {//go to the home view
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
