package controller;

import model.*;
import utils.DatabaseHelper;
import utils.SQLiteConnectionException;
import utils.UnitEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeController implements DatabaseController<Recipe> {
    public RecipeController() {
        // Nothing to do
    }

    public ArrayList<Recipe> getAll() throws FetchDataException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.connectSQLite();
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        int id;
        String name, description;

        try {
            ResultSet rs = dbHelper.execSqlWithReturn("SELECT * FROM Recipe");
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                description = rs.getString(3);
                recipes.add(new Recipe(id, name, description));
            }
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not fetch Recipe information.");
        }
        return recipes;
    }

    public boolean update(Recipe recipe) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.connectSQLite();
        String query = String.format("UPDATE Recipe set Name='%s',Description='%s' WHERE Recipe_ID=%d",
                recipe.getName(), recipe.getDescription(), recipe.getID());
        try {
            dbHelper.execSqlUpdate(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insert(Recipe recipe) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        dbHelper.connectSQLite();
        String query = String.format("INSERT INTO Recipe (Name, Description) VALUES ('%s','%s')",
                recipe.getName(), recipe.getDescription());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public StorageIngredient getIngredient(ArrayList<StorageIngredient> ingredients, int id) throws ObjectNotFoundException {
        for (StorageIngredient ingredient : ingredients) {
            if (ingredient.getID() == id) {
                return ingredient;
            }
        }
        throw new ObjectNotFoundException("Could not find the ingredient.");
    }

    public Recipe getRecipe(Recipe recipe) throws FetchDataException, InvalidIngredientAmountException, EmptyIngredientNameException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        StorageIngredientController sc = new StorageIngredientController();
        dbHelper.connectSQLite();
        ArrayList<RecipeIngredient> ingredients = new ArrayList<RecipeIngredient>();
        ArrayList<StorageIngredient> ingredientCategory = sc.getAll();
        String name, unit;
        double amount;
        String query = String.format("SELECT * FROM Ingredient_in_Recipe WHERE Recipe_ID=%d", recipe.getID());


        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            while (rs.next()) {
                name = getIngredient(ingredientCategory, rs.getInt(2)).getName();
                amount = rs.getDouble(3);
                unit = rs.getString(4);
                ingredients.add(new RecipeIngredient(name, amount, UnitEnum.valueOf(unit)));
            }
        } catch (ObjectNotFoundException | SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not get the recipe's ingredient.");
        }
        recipe.setIngredients(ingredients);
        return recipe;
    }
}
