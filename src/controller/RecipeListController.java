package controller;

import model.Recipe;
import model.RecipeForm;
import model.StorageIngredient;
import utils.FetchDataException;
import view.HomeView;
import view.RecipeDetailView;
import view.RecipeFormView;

import javax.swing.*;

public class RecipeListController {
    public RecipeListController() {
        // Nothing to do
    }

    /**
     * Go back to homepage.
     */
    public void goBack() {
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    /**
     * Go to create new recipe view.
     * @return Whether create of new recipe successfully.
     */
    public boolean newRecipe() {
        try {
            if (StorageIngredient.getAll().size() > 0){
                RecipeForm recipeForm = new RecipeForm(new Recipe(), StorageIngredient.getAll());
                RecipeFormController rfc = new RecipeFormController(recipeForm);
                RecipeFormView rfv = new RecipeFormView(rfc, recipeForm);
                rfv.setVisible(true);
                return true;
            } else {
                return false;
            }
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Go to detail of selected recipe.
     * @param recipe Recipe you want to get detail information.
     */
    public void recipeDetail(Recipe recipe) {
        RecipeDetailController rdc = new RecipeDetailController(recipe);
        RecipeDetailView rdv = new RecipeDetailView(rdc, recipe);
        rdv.setVisible(true);
    }

    /**
     * Go to edit selected recipe.
     * @param recipe Recipe you want to edit.
     */
    public void editRecipe(Recipe recipe) {
        try {
            RecipeForm m = new RecipeForm(recipe, StorageIngredient.getAll());
            m.setRecipeIngredients(recipe.getIngredients());
            RecipeFormController rfc = new RecipeFormController(m);
            RecipeFormView rfv = new RecipeFormView(rfc, m);
            rfv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }


}
