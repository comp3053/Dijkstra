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

    /**
     * Go back to the recommend recipe list page.
     */
    public void goBack() {
        HomeController hc = new HomeController();
        hc.startRecommend();
    }

    /**
     * Change the ingredient batch size in the table.
     * @param originBatchSize Current batch size applied to ingredients.
     * @param currentBatchSize Latest batch size which need to apply to ingredients.
     */
    public void applyBatchSize(int originBatchSize, int currentBatchSize) {
        try {
            m.amountConversion(originBatchSize, currentBatchSize);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    /**
     * Go to brew recipe page and also finish one brew(cost ingredient and add a brew history)
     * @param batchSize Batch size of recipe you brewed.
     */
    public void brewRecipe(int batchSize) {
        BrewRecipeController brc = new BrewRecipeController();
        BrewingRecord br = new BrewingRecord(new Date(), batchSize, m);
        br.insert();
        BrewRecipeView brv = new BrewRecipeView(brc, m, batchSize);
        brv.setVisible(true);
    }
}
