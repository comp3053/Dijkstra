package controller;

import view.RecipeListView;

public class RecipeDetailController {
    public RecipeDetailController(){

    }

    public void goBack(){
        RecipeListController rlc = new RecipeListController();
        RecipeListView rlv = new RecipeListView(rlc);
        rlv.setVisible(true);
    }
}
