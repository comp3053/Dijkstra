package controller;

import model.BrewingRecord;
import model.Recipe;
import utils.EmptyNameException;
import utils.InvalidInputException;
import view.BrewReciptView;

import java.util.Date;

public class BrewDetailController {
    private Recipe m;
    public BrewDetailController(Recipe m){
        this.m = m;
    }

    public void goBack(){
        HomeController hc = new HomeController();
        hc.startRecommend();
    }

    public void applyBatchSize(double originBatchSize, double currentBatchSize){
        try {
            m.amountConversion(originBatchSize, currentBatchSize);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    public void brewRecipe(int batchSize){
        BrewReciptController brc = new BrewReciptController();
        BrewingRecord br = new BrewingRecord(new Date(), batchSize, m);
        try {
            br.insert();
        } catch (EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
        BrewReciptView brv = new BrewReciptView(brc, m, batchSize);
        brv.setVisible(true);
    }
}
