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

    public void startRecommend(ArrayList<Recipe> recommendRecipes) {
        try {
            ArrayList<Integer> notAvailableList = new ArrayList<>();
            RecommendRecipeListController rrlc = new RecommendRecipeListController(recommendRecipes);
            boolean viewStatus = true;
            for (Recipe recipe : recommendRecipes) {
                if (recipe.isAvailable(Equipment.getEquipment(1).getVolume())) {
                    System.out.println("OK recipe: " + recipe.getName());
                    viewStatus = false;
                } else {
                    //recommendRecipes.remove(recipe);
                    notAvailableList.add(recommendRecipes.indexOf(recipe));
                }
            }
            if (!viewStatus) {
                for (int i = notAvailableList.size() - 1; i >= 0; i--) {
                    recommendRecipes.remove(notAvailableList.get(i).intValue());
                }
            }
            recommendRecipes.sort(new CustomRecipeComparator());
            RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc, recommendRecipes, viewStatus);
            rrlv.setVisible(true);
        } catch (FetchDataException | InvalidInputException | EmptyNameException e) {
            e.printStackTrace();
        }
    }
}
