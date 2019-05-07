package controller;

import model.StorageIngredient;
import utils.FetchDataException;
import view.IngredientListView;

public class IngredientDetailController {
    public IngredientDetailController(){

    }
    public void goBack(){
        IngredientListController ilc = new IngredientListController();
        try {
            IngredientListView ilv = new IngredientListView(ilc, StorageIngredient.getAll());
            ilv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }
}
