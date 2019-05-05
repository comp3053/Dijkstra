package controller;

import model.Ingredient;
import view.HomeView;
import view.IngredientDetailView;
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

    public void ingredientDetail(Ingredient ingredient){
        IngredientDetailController idc = new IngredientDetailController();
        IngredientDetailView idv = new IngredientDetailView(idc,ingredient);
        idv.setVisible(true);
    }
}
