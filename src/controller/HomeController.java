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

    public void startManageRecipe() {
        RecipeListController rlc = new RecipeListController();
        try {
            RecipeListView rlv = new RecipeListView(rlc, Recipe.getAll());
            rlv.setVisible(true);
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

    public void startManageIngredient() {
        IngredientListController ilc = new IngredientListController();
        try {
            IngredientListView ilv = new IngredientListView(ilc, StorageIngredient.getAll());
            ilv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }

    public void startNoteList() {
        NoteListController nlc = new NoteListController();
        NoteListView nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }

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

    public void startRecommend() {
        try {
            ArrayList<Recipe> recommendRecipe = Recipe.getAll();
            ArrayList<Integer> notAvailableList = new ArrayList<>();
            RecommendRecipeListController rrlc = new RecommendRecipeListController(recommendRecipe);
            boolean viewStatus = true;
            for (Recipe recipe : recommendRecipe) {
                if (recipe.isAvailable(Equipment.getEquipment(1).getVolume())) {
                    System.out.println("OK recipe: " + recipe.getName());
                    viewStatus = false;
                } else {
                    //recommendRecipe.remove(recipe);
                    notAvailableList.add(recommendRecipe.indexOf(recipe));
                }
            }
            if (!viewStatus) {
                for (int i = notAvailableList.size() - 1; i >= 0; i--) {
                    recommendRecipe.remove(notAvailableList.get(i).intValue());
                }
            }
            Collections.sort(recommendRecipe, new CustomRecipeComparator());
            RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc, recommendRecipe, viewStatus);
            rrlv.setVisible(true);
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
