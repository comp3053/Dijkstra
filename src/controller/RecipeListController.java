package controller;

import view.HomeView;
import view.RecipeFormView;

public class RecipeListController {
    public RecipeListController() {

    }

    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void newRecipe() {
        RecipeFormController rfc = new RecipeFormController();
        RecipeFormView rfv = new RecipeFormView(rfc);
        rfv.setVisible(true);
    }
}
