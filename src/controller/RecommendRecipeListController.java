package controller;

import model.Recipe;
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

    public void brewRecipe(Recipe recipe){
        BrewDetailController bdc = new BrewDetailController();
        BrewDetailView bdv = new BrewDetailView(bdc,recipe);
        bdv.setVisible(true);
    }

    public void generateShoppingList(Recipe recipe){
        MissingIngredientListController milc = new MissingIngredientListController();
        MissingIngredientsListView milv = new MissingIngredientsListView(milc,recipe);
        milv.setVisible(true);
    }
}
