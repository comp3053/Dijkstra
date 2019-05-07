package controller;

import view.BrewDetailView;
import view.HomeView;
import view.MissingIngredientsListView;

public class RecommendRecipeListController {
    public RecommendRecipeListController(){

    }

    public void goBack(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }

    public void brewRecipe(){
        BrewDetailController bdc = new BrewDetailController();
        BrewDetailView bdv = new BrewDetailView(bdc);
        bdv.setVisible(true);
    }

    public void generateShoppingList(){
        MissingIngredientListController milc = new MissingIngredientListController();
        MissingIngredientsListView milv = new MissingIngredientsListView(milc);
        milv.setVisible(true);
    }
}
