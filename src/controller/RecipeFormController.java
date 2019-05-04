package controller;

import view.RecipeListView;

public class RecipeFormController {
    public RecipeFormController(){

    }

    public void saveRecipe() {
        System.out.println("Save!");
        // TODO, check the recipe and save it
    }

    public void cancel(){
        RecipeListController rlc = new RecipeListController();
        RecipeListView rlv = new RecipeListView(rlc);
        rlv.setVisible(true);
    }
}
