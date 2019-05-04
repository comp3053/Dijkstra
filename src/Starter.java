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
//
                RecipeListController rlc = new RecipeListController();
                RecipeListView rv = new RecipeListView(rlc);
                rv.setVisible(true);
//
//                RecipeFormController rfc = new RecipeFormController();
//                RecipeFormView rfv = new RecipeFormView(rfc);
//                rfv.setVisible(true);
//
//                UpdateEquipmentInforController ueic = new UpdateEquipmentInforController();
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
                MissingIngredientListController milc = new MissingIngredientListController();
                MissingIngredientsListView milv = new MissingIngredientsListView(milc);
                milv.setVisible(true);
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
                RecipeDetailController rdc = new RecipeDetailController();
                RecipeDetailView rdv = new RecipeDetailView(rdc);
                rdv.setVisible(true);
//
//
                IngredientListController ilc = new IngredientListController();
                IngredientListView ilv = new IngredientListView(ilc);
                ilv.setVisible(true);
//
//
//                IngredientFormController ifc = new IngredientFormController();
//                IngredientFormView ifv = new IngredientFormView(ifc);
//                ifv.setVisible(true);
//
//
                IngredientDetailController idc = new IngredientDetailController();
                IngredientDetailView idv = new IngredientDetailView(idc);
                idv.setVisible(true);
//
//
//                BrewDetailController bdc = new BrewDetailController();
//                BrewDetailView bdv = new BrewDetailView(bdc);
//                bdv.setVisible(true);
//
//
                BrewReciptController brc = new BrewReciptController();
                BrewReciptView brv = new BrewReciptView(brc);
                brv.setVisible(true);


//                LayoutTestView ltv = new LayoutTestView();
//                ltv.setVisible(true);
            }
        });
    }
}
