package controller;

import view.IngredientListView;

public class IngredientFormController {
    public IngredientFormController(){

    }

    public void saveIngredient(){
        System.out.println("Saved");
    }

    public void cancel(){
        // TODO: Pass in a Ingredient ArrayList
        IngredientListController ilc = new IngredientListController();
        IngredientListView ilv = new IngredientListView(ilc);
        ilv.setVisible(true);
    }
}
