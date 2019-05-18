package controller;

import model.StorageIngredient;
import utils.FetchDataException;
import view.IngredientFormView;
import view.IngredientListView;

public class IngredientDetailController {
    public IngredientDetailController() {
        // Nothing to do
    }

    /**
     * Go back to ingredient list.
     */
    public void goBack() {
        IngredientListController ilc = new IngredientListController();
        try {
            IngredientListView ilv = new IngredientListView(ilc, StorageIngredient.getAll());
            ilv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Go to edit selected storage ingredient.
     * @param ingredient Current storage ingredient information.
     */
    public void editIngredient(StorageIngredient ingredient) {
        IngredientFormController ifc = new IngredientFormController(ingredient);
        IngredientFormView ifv = new IngredientFormView(ifc, ingredient);
        ifv.setVisible(true);
    }
}
