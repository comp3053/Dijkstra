package controller;

import model.StorageIngredient;
import view.HomeView;
import view.IngredientDetailView;
import view.IngredientFormView;

public class IngredientListController {
    public IngredientListController(){
        // Nothing to do
    }

    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void addIngredient(){
        IngredientFormController ifc = new IngredientFormController(new StorageIngredient());
        IngredientFormView ifv = new IngredientFormView(ifc, new StorageIngredient());
        ifv.setVisible(true);
    }

    public void editIngredient(StorageIngredient ingredient){
        IngredientFormController ifc = new IngredientFormController(ingredient);
        IngredientFormView ifv = new IngredientFormView(ifc, ingredient);
        ifv.setVisible(true);
    }

    public void ingredientDetail(StorageIngredient ingredient){
        IngredientDetailController idc = new IngredientDetailController();
        IngredientDetailView idv = new IngredientDetailView(idc,ingredient);
        idv.setVisible(true);
    }
}
