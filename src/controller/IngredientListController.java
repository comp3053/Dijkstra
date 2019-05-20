package controller;

import model.StorageIngredient;
import view.HomeView;
import view.IngredientDetailView;
import view.IngredientFormView;

public class IngredientListController {
    public IngredientListController() {
        // Nothing to do
    }

    /**
     * Go back to home page.
     */
    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    /**
     * Go to add ingredient page.
     */
    public void addIngredient() {
        IngredientFormController ifc = new IngredientFormController(new StorageIngredient());
        IngredientFormView ifv = new IngredientFormView(ifc, new StorageIngredient());
        ifv.setVisible(true);
    }

    /**
     * Go to edit selected ingredient.
     * @param ingredient Ingredient you want to edit.
     */
    public void editIngredient(StorageIngredient ingredient) {
        IngredientFormController ifc = new IngredientFormController(ingredient);
        IngredientFormView ifv = new IngredientFormView(ifc, ingredient);
        ifv.setVisible(true);
    }

    /**
     * Go to watch detail of selected ingredient.
     * @param ingredient Ingredient you want to watch detail.
     */
    public void ingredientDetail(StorageIngredient ingredient) {
        IngredientDetailController idc = new IngredientDetailController();
        IngredientDetailView idv = new IngredientDetailView(idc, ingredient);
        idv.setVisible(true);
    }
}
