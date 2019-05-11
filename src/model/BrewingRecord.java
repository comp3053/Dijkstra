package model;

import utils.DatabaseHelper;
import utils.EmptyNameException;
import utils.InvalidInputException;
import utils.SQLiteConnectionException;

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

    public boolean insert() throws EmptyNameException, InvalidInputException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("INSERT INTO Brew (Brew_Date,Batch_Size,Recipe_ID,Note_ID) VALUES" +
                "(%d,%d,%d,0)", brewDate.getTime(), batchSize, recipe.getID());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        // Update StorageIngredient amount
        for (RecipeIngredient ingredient : recipe.getIngredients()) {
            StorageIngredient storageIngredient = new StorageIngredient(ingredient.getID());
            storageIngredient.setAmount(storageIngredient.getAmount() - ingredient.getAmount());
            storageIngredient.update();
        }
        return true;
    }

    public boolean delete() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("DELETE FROM Brew WHERE Brew_ID=%d", id);
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
