import controller.*;
import model.Equipment;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
import view.*;

import javax.swing.*;


public class Starter {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // run that code on the event dispatch thread
            @Override
            public void run() {
                try {
                    Equipment equipment = Equipment.getEquipment(1);
                    HomeController hc = new HomeController();
                    HomeView hv = new HomeView(hc);
                    hv.setVisible(true);
                } catch (FetchDataException | EmptyNameException | InvalidInputException e) {
                    int isSave = JOptionPane.showConfirmDialog(null,
                            "There is no equipment information in database, would you like to add one?",
                            "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (isSave == JOptionPane.YES_OPTION) {
                        Equipment m = null;
                        try {
                            m = new Equipment("dummy", 1000);
                        } catch (InvalidInputException | EmptyNameException ex) {
                            ex.printStackTrace();
                        }
                        UpdateEquipmentInfoController ueic = new UpdateEquipmentInfoController(m);
                        UpdateEquipmentInfoView ueiv = new UpdateEquipmentInfoView(ueic, m, true);
                        ueiv.setVisible(true);
                    } else {
                        System.exit(-1);
                    }

                }
//
//                RecipeListController rlc = new RecipeListController();
//                RecipeListView rv = new RecipeListView(rlc);
//                rv.setVisible(true);
//
//                RecipeFormController rfc = new RecipeFormController();
//                RecipeFormView rfv = new RecipeFormView(rfc);
//                rfv.setVisible(true);
//
//                UpdateEquipmentInfoController ueic = new UpdateEquipmentInfoController();
//                UpdateEquipmentInfoView ueiv = new UpdateEquipmentInfoView(ueic);
//                ueiv.setVisible(true);
//
//                EquipmentInfoController eic = new EquipmentInfoController();
//                EquipmentInfoView ei = new EquipmentInfoView(eic);
//                ei.setVisible(true);
//
//                NoteInputController nic = new NoteInputController();
//                NoteInputView niv = new NoteInputView(nic, 3);
//                niv.setVisible(true);
//
//                BrewingHistoryListController bhlc = new BrewingHistoryListController();
//                BrewingHistoryListView bhlv = new BrewingHistoryListView(bhlc);
//                bhlv.setVisible(true);
//
//
//                NoteContentController ncc = new NoteContentController();
//                NoteContentView ncv = new NoteContentView(ncc, 1);
//                ncv.setVisible(true);
//
//
//                NoteListController nlc = new NoteListController();
//                NoteListView nlv = new NoteListView(nlc);
//                nlv.setVisible(true);
//
//
//                MissingIngredientListController milc = new MissingIngredientListController();
//                MissingIngredientsListView milv = new MissingIngredientsListView(milc);
//                milv.setVisible(true);
//
//
//                RecommendNotEnoughIngredientRecipeListController rilc = new RecommendNotEnoughIngredientRecipeListController();
//                RecommendNotEnoughIngredientRecipeListView rilv = new RecommendNotEnoughIngredientRecipeListView(rilc);
//                rilv.setVisible(true);
//
//                RecommendRecipeListController rrlc = new RecommendRecipeListController();
//                RecommendRecipeListView rrlv = new RecommendRecipeListView(rrlc);
//                rrlv.setVisible(true);
//
//
//                RecipeDetailController rdc = new RecipeDetailController();
//                RecipeDetailView rdv = new RecipeDetailView(rdc);
//                rdv.setVisible(true);
//
//
//                IngredientListController ilc = new IngredientListController();
//                IngredientListView ilv = new IngredientListView(ilc);
//                ilv.setVisible(true);
//
//
//                IngredientFormController ifc = new IngredientFormController();
//                IngredientFormView ifv = new IngredientFormView(ifc);
//                ifv.setVisible(true);
//
//
//                IngredientDetailController idc = new IngredientDetailController();
//                IngredientDetailView idv = new IngredientDetailView(idc);
//                idv.setVisible(true);
//
//
//                BrewDetailController bdc = new BrewDetailController();
//                BrewDetailView bdv = new BrewDetailView(bdc);
//                bdv.setVisible(true);
//
//
//                BrewReciptController brc = new BrewReciptController();
//                BrewReciptView brv = new BrewReciptView(brc);
//                brv.setVisible(true);
//
            }
        });
    }
}
