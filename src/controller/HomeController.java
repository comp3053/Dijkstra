package controller;

import model.Equipment;
import model.Recipe;
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
        RecipeListView rlv = new RecipeListView(rlc);
        rlv.setVisible(true);
    }
    public void startManageIngredient(){
        // TODO: Pass in a Ingredient ArrayList
        IngredientListController ilc = new IngredientListController();
        IngredientListView ilv = new IngredientListView(ilc);
        ilv.setVisible(true);
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
        RecipeController rc = new RecipeController();
        try {
            ArrayList<Recipe> recommendRecipe = rc.getAll();
            RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc,recommendRecipe);
            rrlv.setVisible(true);
        } catch (FetchDataException e) {
            e.printStackTrace();
        }
    }
}
