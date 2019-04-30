package controller;

import model.BrewingRecord;
import model.Recipe;
import utils.DatabaseHelper;
import utils.SQLiteConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BrewController implements DatabaseController<BrewingRecord> {
    public BrewController() {
        //Nothing to do
    }

    public Recipe getRecipe(int recipeID) throws FetchDataException, ObjectNotFoundException {
        RecipeController rc = new RecipeController();
        ArrayList<Recipe> recipes = rc.getAll();
        for (Recipe recipe : recipes) {
            if (recipe.getID() == recipeID)
                return recipe;
        }
        throw new ObjectNotFoundException("Could not get corresponding recipe.");
    }

    public ArrayList<BrewingRecord> getAll() throws FetchDataException, ObjectNotFoundException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.connectSQLite();
        ArrayList<BrewingRecord> brewingRecords = new ArrayList<>();
        int id, batchSize;
        Date brewDate;
        Recipe recipe;

        try {
            ResultSet rs = dbHelper.execSqlWithReturn("SELECT * FROM Brew");
            id = rs.getInt(1);
            brewDate = new Date(rs.getLong(2));
            batchSize = rs.getInt(3);
            recipe = getRecipe(rs.getInt(4));
            brewingRecords.add(new BrewingRecord(id, brewDate, batchSize, recipe));
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not get Brew Records.");
        }
        return brewingRecords;
    }

    public boolean insert(BrewingRecord brewingRecord) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.connectSQLite();
        String query = String.format("INSERT INTO Brew (Brew_Date,Batch_Size,Recipe_ID) VALUES (%d,%d,%d)",
                brewingRecord.getBrewDate().getTime(), brewingRecord.getBatchSize(),
                brewingRecord.getRecipe().getID());

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
