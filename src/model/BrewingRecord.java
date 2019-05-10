package model;

import utils.DatabaseHelper;
import utils.SQLiteConnectionException;
import java.util.Date;

public class BrewingRecord{
    private int id;
    private Date brewDate;
    private double batchSize;
    private Recipe recipe;

    public BrewingRecord(Date brew_date, double batch_size, Recipe recipe) {
        setBatchSize(batch_size);
        setBrewDate(brew_date);
        setRecipe(recipe);
    }

    public BrewingRecord(int id, Date brew_date, double batch_size, Recipe recipe) {
        setID(id);
        setBatchSize(batch_size);
        setBrewDate(brew_date);
        setRecipe(recipe);
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Date getBrewDate() {
        return brewDate;
    }

    public void setBrewDate(Date brewDate) {
        this.brewDate = brewDate;
    }

    public double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(double batchSize) {
        this.batchSize = batchSize;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    private void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
