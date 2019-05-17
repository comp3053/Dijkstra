package controller;

import model.BrewingRecord;
import model.Recipe;
import utils.InvalidInputException;
import view.BrewRecipeView;

import java.util.ArrayList;
import java.util.Date;

public class BrewDetailController {
    private Recipe m;

    public BrewDetailController(Recipe m) {//initial the brew detail controller
        this.m = m;
    }

    public void goBack() {// go back to the recommend recipe list page
        HomeController hc = new HomeController();
        hc.startRecommend();
    }

    public void applyBatchSize(int originBatchSize, int currentBatchSize) {// change the ingredient batch size in the table
        try {
            m.amountConversion(originBatchSize, currentBatchSize);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    public void brewRecipe(int batchSize) {// go to brew recipe page and also finish one brew(cost ingredient and add a brew history)
        BrewRecipeController brc = new BrewRecipeController();
        BrewingRecord br = new BrewingRecord(new Date(), batchSize, m);
        br.insert();
        BrewRecipeView brv = new BrewRecipeView(brc, m, batchSize);
        brv.setVisible(true);
    }
}
