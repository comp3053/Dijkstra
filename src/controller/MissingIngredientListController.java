package controller;

import model.Recipe;
import view.HomeView;

import java.util.ArrayList;

public class MissingIngredientListController {
    public MissingIngredientListController() {
        // Nothing to do
    }

    public void goBack() {// go back to the recommend list view
        HomeController hc = new HomeController();
        hc.startRecommend();
    }

    public void OK() {// go back to the home view
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
