package test;

import model.BrewingRecord;
import model.Recipe;
import model.RecipeIngredient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BrewingRecordTest {

    //initial part
    private static BrewingRecord brewingRecord;
    java.util.Date date = new java.util.Date();
    ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
    Recipe recipe = new Recipe(1,"beer","good",recipeIngredients);

    @Before
    public void setUp() {//set up a brewing record
        brewingRecord = new BrewingRecord(1,date,10,recipe);
    }

    @Test
    public void getID() {// test whether it can get the id
        assertEquals(1,brewingRecord.getID());
    }

    @Test
    public void setID() {// test whether it can set the id
        brewingRecord.setID(2);
        assertEquals(2,brewingRecord.getID());
    }

    @Test
    public void getBrewDate() {//test whether it can get the brew date
        assertEquals(date,brewingRecord.getBrewDate());
    }

    @Test
    public void setBrewDate() {//test whether it can set the bre date
        java.util.Date modifyDate = new java.util.Date();
        brewingRecord.setBrewDate(modifyDate);
        assertEquals(modifyDate,brewingRecord.getBrewDate());
    }

    @Test
    public void getBatchSize() {// test whether it can get the batch size
        assertEquals(10,brewingRecord.getBatchSize());
    }

    @Test
    public void setBatchSize() {// test whether it can set the batch size
        brewingRecord.setBatchSize(20);
        assertEquals(20,brewingRecord.getBatchSize());
    }
}