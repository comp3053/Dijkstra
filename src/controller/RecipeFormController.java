package controller;

import model.Recipe;
import model.RecipeForm;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
import view.RecipeListView;

public class RecipeFormController {
    private RecipeForm m;

    public RecipeFormController(RecipeForm m){
        this.m = m;
    }

    public void saveRecipe() {
        System.out.println(m.getRecipeIngredients().get(0).getName());
        m.save();
        cancel();
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
