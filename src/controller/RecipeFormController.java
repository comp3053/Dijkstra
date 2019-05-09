package controller;

import model.Recipe;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
import view.RecipeListView;

public class RecipeFormController {
    private Recipe m;

    public RecipeFormController(Recipe m){
        this.m = m;
    }

    public void saveRecipe() {
        System.out.println("Save!");
        // TODO, check the recipe and save it
    }

    public void cancel(){
        RecipeListController rlc = new RecipeListController();
        try {
            RecipeListView rlv = new RecipeListView(rlc, Recipe.getAll());
            rlv.setVisible(true);
        }  catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
