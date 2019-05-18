package controller;

import view.HomeView;

public class MissingIngredientListController {
    public MissingIngredientListController() {
        // Nothing to do
    }

    /**
     * Go back to recommend list view.
     */
    public void goBack() {
        HomeController hc = new HomeController();
        hc.startRecommend();
    }

    /**
     * Go back to homepage.
     */
    public void OK() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
