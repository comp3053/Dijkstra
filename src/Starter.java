import controller.*;
import view.*;


public class Starter {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() { // run that code on the event dispatch thread
            @Override
            public void run() {
//                HomeController hc = new HomeController();
//                HomeView hv = new HomeView(hc);
//                hv.setVisible(true);

//                RecipeView rv = new RecipeView();
//                rv.setVisible(true);

//                RecipeFormView rfv = new RecipeFormView();
//                rfv.setVisible(true);

//                UpdateEquipmentInforController ueic = new UpdateEquipmentInforController();
//                UpdateEquipmentInfoView ueiv = new UpdateEquipmentInfoView(ueic);
//                ueiv.setVisible(true);

//                EquipmentInfoController eic = new EquipmentInfoController();
//                EquipmentInfoView ei = new EquipmentInfoView(eic);
//                ei.setVisible(true);

//                NoteInputController nic = new NoteInputController();
//                NoteInputView niv = new NoteInputView(nic);
//                niv.setVisible(true);

//                BrewingHistoryListController bhlc = new BrewingHistoryListController();
//                BrewingHistoryListView bhlv = new BrewingHistoryListView(bhlc);
//                bhlv.setVisible(true);

//                NoteContentController ncc = new NoteContentController();
//                NoteContentView ncv = new NoteContentView(ncc);
//                ncv.setVisible(true);

//                NoteListController nlc = new NoteListController();
//                NoteListView nlv = new NoteListView(nlc);
//                nlv.setVisible(true);

//                MissingIngredientListController milc = new MissingIngredientListController();
//                MissingIngredientsListView milv = new MissingIngredientsListView(milc);
//                milv.setVisible(true);

//                RecommendIngredientListController rilc = new RecommendIngredientListController();
//                RecommendIngredientListView rilv = new RecommendIngredientListView(rilc);
//                rilv.setVisible(true);

//                RecipeDetailView rdv = new RecipeDetailView();
//                rdv.setVisible(true);

//                IngredientListView ilv = new IngredientListView();
//                ilv.setVisible(true);

//                IngredientFormView ifv = new IngredientFormView();
//                ifv.setVisible(true);

                IngredientDetailView idv = new IngredientDetailView();
                idv.setVisible(true);
            }
        });
    }
}
