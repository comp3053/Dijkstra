package controller;

import model.Equipment;
import model.Recipe;
import model.StorageIngredient;
import utils.CustomRecipeComparator;
import utils.EmptyNameException;
import utils.InvalidInputException;
import utils.FetchDataException;
import view.*;

import java.util.ArrayList;
import java.util.Collections;

public class HomeController {
    public HomeController() {
        // Nothing to do
    }

    /**
     * Go to the Recipe List view
     */
    public void startManageRecipe() {
        RecipeListController rlc = new RecipeListController();
        try {
            RecipeListView rlv = new RecipeListView(rlc, Recipe.getAll());
            rlv.setVisible(true);
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

    /**
     * Go to the Ingredient List view
     */
    public void startManageIngredient() {
        IngredientListController ilc = new IngredientListController();
        try {
            IngredientListView ilv = new IngredientListView(ilc, StorageIngredient.getAll());
            ilv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Go to the Note List view
     */
    public void startNoteList() {
        NoteListController nlc = new NoteListController();
        NoteListView nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }

    /**
     * Go to the equipment information view
     */
    public void startEquipmentInformation() {
        Equipment equipment;
        try {
            equipment = Equipment.getEquipment(1);
            EquipmentInfoController eic = new EquipmentInfoController(equipment);
            EquipmentInfoView ei = new EquipmentInfoView(eic, equipment);
            equipment.setModelListener(ei);
            ei.setVisible(true);
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

    /**
     * Go to the recommend recipe list or shopping list view
     */
    public void startRecommend() {
        try {
            ArrayList<Recipe> recommendRecipe = Recipe.getAll();
            ArrayList<Integer> notAvailableList = new ArrayList<>();
            RecommendRecipeListController rrlc = new RecommendRecipeListController(recommendRecipe);
            boolean viewStatus = true;// Judge to show shopping list view or recommend recipe list view
            // Judge if there is any recipe can brew
            for (Recipe recipe : recommendRecipe) {
                // According to the batch size of equipment ,
                // if one recipe can brew, show the recommend recipe list view, else show the shopping list view.
                if (recipe.isAvailable(Equipment.getEquipment(1).getVolume())) {
                    viewStatus = false;
                } else {
                    notAvailableList.add(recommendRecipe.indexOf(recipe));
                }
            }
            // If there is some recipe available,
            // we need to remove the unavailable recipe and display them on recommend recipe list view
            if (!viewStatus) {
                for (int i = notAvailableList.size() - 1; i >= 0; i--) {
                    recommendRecipe.remove(notAvailableList.get(i).intValue());
                }
            }
            recommendRecipe.sort(new CustomRecipeComparator());
            RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc, recommendRecipe, viewStatus);
            rrlv.setVisible(true);
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
