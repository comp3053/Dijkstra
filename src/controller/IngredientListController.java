package controller;

import view.HomeView;
import view.IngredientFormView;

public class IngredientListController {
    public IngredientListController(){

    }

    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void addIngredient(){
        IngredientFormController ifc = new IngredientFormController();
        IngredientFormView ifv = new IngredientFormView(ifc);
        ifv.setVisible(true);
    }
}
