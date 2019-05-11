package controller;

import model.Recipe;
import utils.EmptyNameException;
import utils.FetchDataException;
import utils.InvalidInputException;
import view.HomeView;
import view.MissingIngredientsListView;
import view.RecommendRecipeListView;

import java.util.ArrayList;

public class MissingIngredientListController {
    public MissingIngredientListController(){

    }

    public void readMissingIngredientList(){

    }

    public void goBack(){
        HomeController hc = new HomeController();
        hc.startRecommend();
    }

    public void OK(){
        HomeController hc = new HomeController();
        HomeView hv = new HomeView(hc);
        hv.setVisible(true);
    }
}
