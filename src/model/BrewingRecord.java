package model;

import controller.RecipeController;
import utils.DatabaseHelper;
import utils.FetchDataException;
import utils.ObjectNotFoundException;
import utils.SQLiteConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public static Recipe getRecipe(int recipeID) throws FetchDataException, ObjectNotFoundException {
        RecipeController rc = new RecipeController();
        ArrayList<Recipe> recipes = rc.getAll();
        for (Recipe recipe : recipes) {
            if (recipe.getID() == recipeID)
                return recipe;
        }
        throw new ObjectNotFoundException("Could not get corresponding recipe.");
    }

    public static ArrayList<BrewingRecord> getAllBrewingRecord() throws FetchDataException, ObjectNotFoundException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        ArrayList<BrewingRecord> brewingRecords = new ArrayList<>();
        int id, batchSize;
        Date brewDate;
        Recipe recipe;
        try {
            ResultSet rs = dbHelper.execSqlWithReturn("SELECT * FROM Brew WHERE NOTE_ID == 0");
            while(rs.next()) {
                id = rs.getInt(1);
                brewDate = new Date(rs.getLong(2));
                batchSize = rs.getInt(3);
                recipe = getRecipe(rs.getInt(4));
                brewingRecords.add(new BrewingRecord(id, brewDate, batchSize, recipe));
            }
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not get Brew Records.");
        }
        return brewingRecords;
    }
}