package controller;

import view.HomeView;

public class BrewRecipeController {
    public BrewRecipeController() {

    }

    /**
     * Go back to homepage.
     */
    public void finish() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
