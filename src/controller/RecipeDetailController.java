package controller;

import model.Recipe;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
import view.RecipeListView;

public class RecipeDetailController {
    private Recipe m;

    public RecipeDetailController(Recipe m) {
        this.m = m;
    }

    /**
     * Go back to RecipeListView.
     */
    public void goBack() {
        RecipeListController rlc = new RecipeListController();
        try {
            RecipeListView rlv = new RecipeListView(rlc, Recipe.getAll());
            rlv.setVisible(true);
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
