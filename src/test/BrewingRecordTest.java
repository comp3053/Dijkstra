package test;

import model.BrewingRecord;
import model.Recipe;
import model.RecipeIngredient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BrewingRecordTest {

    private static BrewingRecord brewingRecord;
    java.util.Date date = new java.util.Date();
    ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
    Recipe recipe = new Recipe(1,"beer","good",recipeIngredients);

    @Before
    public void setUp() {
        brewingRecord = new BrewingRecord(1,date,10,recipe);
    }

    @Test
    public void getID() {
        assertEquals(1,brewingRecord.getID());
    }

    @Test
    public void setID() {
        brewingRecord.setID(2);
        assertEquals(2,brewingRecord.getID());
    }

    @Test
    public void getBrewDate() {
        assertEquals(date,brewingRecord.getBrewDate());
    }

    @Test
    public void setBrewDate() {
        java.util.Date modifyDate = new java.util.Date();
        brewingRecord.setBrewDate(modifyDate);
        assertEquals(modifyDate,brewingRecord.getBrewDate());
    }

    @Test
    public void getBatchSize() {
        assertEquals(10,brewingRecord.getBatchSize());
    }

    @Test
    public void setBatchSize() {
        brewingRecord.setBatchSize(20);
        assertEquals(20,brewingRecord.getBatchSize());
    }
}