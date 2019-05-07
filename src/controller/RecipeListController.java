package controller;

import model.Recipe;
import view.HomeView;
import view.RecipeDetailView;
import view.RecipeFormView;

public class RecipeListController {
    public RecipeListController() {
        // Nothing to do
    }

    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void newRecipe() {
        Recipe recipe = new Recipe();
        RecipeFormController rfc = new RecipeFormController(recipe);
        RecipeFormView rfv = new RecipeFormView(rfc, recipe);
        rfv.setVisible(true);
    }

    public void recipeDetail(Recipe recipe) {
        RecipeDetailController rdc = new RecipeDetailController(recipe);
        RecipeDetailView rdv = new RecipeDetailView(rdc, recipe);
        rdv.setVisible(true);
    }

    public void editRecipe(Recipe recipe) {
        RecipeFormController rfc = new RecipeFormController(recipe);
        RecipeFormView rfv = new RecipeFormView(rfc, recipe);
        rfv.setVisible(true);
    }


}
