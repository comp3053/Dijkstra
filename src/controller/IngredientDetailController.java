package controller;

import view.IngredientListView;

public class IngredientDetailController {
    public IngredientDetailController(){

    }
    public void goBack(){
        IngredientListController ilc = new IngredientListController();
        IngredientListView ilv = new IngredientListView(ilc);
        ilv.setVisible(true);
    }
}
