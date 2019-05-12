package model;

import controller.ModelListener;
import utils.DatabaseHelper;
import utils.EmptyNameException;
import utils.InvalidInputException;
import utils.SQLiteConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BrewingRecord implements IDatabaseOperation<BrewingRecord> {
    private int id;
    private Date brewDate;
    private int batchSize;
    private Recipe recipe;
    private ModelListener listener;

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

    @Override
    public boolean insert() {
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
            try {
                StorageIngredient storageIngredient = new StorageIngredient(ingredient.getID());
                storageIngredient.setAmount(storageIngredient.getAmount() - ingredient.getAmount());
                storageIngredient.update();
            } catch (EmptyNameException | InvalidInputException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean update() {
        // Brewing Record do not have an update
        return false;
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

    public static ArrayList<BrewingRecord> getAll() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = "SELECT * FROM Brew WHERE Note_ID=0";
        ArrayList<BrewingRecord> brewingRecords = new ArrayList<>();
        int id, batchSize, recipeID;
        Date date;

        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            while (rs.next()) {
                id = rs.getInt(1);
                batchSize = rs.getInt(3);
                date = new Date(rs.getLong(2));
                recipeID = rs.getInt(4);
                brewingRecords.add(new BrewingRecord(id, date, batchSize, new Recipe(recipeID)));
            }
            dbHelper.closeConnection();
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
        }
        return brewingRecords;
    }

    @Override
    public void addListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyListener() {
        this.listener.update();
    }
}