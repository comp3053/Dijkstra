package controller;

import model.BrewingRecord;
import model.Recipe;
import utils.InvalidInputException;
import view.BrewRecipeView;

import java.util.ArrayList;
import java.util.Date;

public class BrewDetailController {
    private Recipe m;

    public BrewDetailController(Recipe m) {
        this.m = m;
    }

    public void goBack() {
        HomeController hc = new HomeController();
        hc.startRecommend();
    }

    public void applyBatchSize(int originBatchSize, int currentBatchSize) {
        try {
            m.amountConversion(originBatchSize, currentBatchSize);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    public void brewRecipe(int batchSize) {
        BrewRecipeController brc = new BrewRecipeController();
        BrewingRecord br = new BrewingRecord(new Date(), batchSize, m);
        br.insert();
        BrewRecipeView brv = new BrewRecipeView(brc, m, batchSize);
        brv.setVisible(true);
    }
}
