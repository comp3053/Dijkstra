package controller;

import model.Equipment;
import model.Recipe;
import model.StorageIngredient;
import utils.EmptyNameException;
import utils.InvalidInputException;
import utils.FetchDataException;
import view.*;

import java.util.ArrayList;

public class HomeController {
    public HomeController(){
        // Nothing to do
    }
    public void startManageRecipe(){
        RecipeListController rlc = new RecipeListController();
        try {
            RecipeListView rlv = new RecipeListView(rlc, Recipe.getAll());
            rlv.setVisible(true);
        } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
    public void startManageIngredient(){
        IngredientListController ilc = new IngredientListController();
        try {
            IngredientListView ilv = new IngredientListView(ilc, StorageIngredient.getAll());
            ilv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }
    public void startNoteList(){
        NoteListController nlc = new NoteListController();
        NoteListView nlv = new NoteListView(nlc);
        nlv.setVisible(true);
    }
    public void startEquipmentInformation(){
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
    public void startRecommend(){
// TODO: Check if there are enough ingredient
        RecommendRecipeListController rrlc = new RecommendRecipeListController();
        try {
            ArrayList<Recipe> recommendRecipe =  new Recipe().getAll();
            RecommendRecipeListView rrlv;
            rrlv = new RecommendRecipeListView(rrlc,recommendRecipe, true);
            for (int i = 0; i<recommendRecipe.size();i++) {
                if (recommendRecipe.get(i).isAvailable()) {
                    rrlv = new RecommendRecipeListView(rrlc,recommendRecipe, false);
                }
            }
            rrlv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        } catch (EmptyNameException e) {
            e.printStackTrace();
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
