package model;

import java.util.Date;

public class BrewingRecord {
    private int id;
    private Date brewDate;
    private int batchSize;
    private Recipe recipe;

    public BrewingRecord(Date brew_date, int batch_size, Recipe recipe) {
        setBatchSize(batch_size);
        setBrewDate(brew_date);
        setRecipe(recipe);
    }

    public BrewingRecord(int id, Date brew_date, int batch_size, Recipe recipe) {
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

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    private void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
