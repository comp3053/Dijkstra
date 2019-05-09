package model;

import controller.ModelListener;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeIngredient extends Ingredient implements IDatabaseOperation<RecipeIngredient> {
    private ModelListener listener;
    private int recipeID;

    public RecipeIngredient(int ingredientID, String name, double amount, UnitEnum unit) throws EmptyNameException,
            InvalidInputException {
        super(ingredientID, name, amount, unit);
    }

    public RecipeIngredient(int ingredientID, String name, double amount, UnitEnum unit, int recipeID) throws EmptyNameException,
            InvalidInputException {
        super(ingredientID, name, amount, unit);
        this.recipeID = recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    @Override
    public void addListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyListener() {
        listener.update();
    }

    @Override
    public boolean insert() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("INSERT INTO Ingredient_in_Recipe VALUES (%d,%d,%f,'%s')",
                this.getRecipeID(), this.getID(), this.getAmount(), this.getUnit());

        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("DELETE FROM Ingredient_in_Recipe WHERE Recipe_ID=%d AND Ingredient_ID=%d",
                this.getRecipeID(), this.getID());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<RecipeIngredient> getAll(int recipeID) throws FetchDataException, EmptyNameException, InvalidInputException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        ArrayList<RecipeIngredient> ingredients = new ArrayList<>();
        int ingredientID;
        String name;
        double amount;
        UnitEnum unit;
        String query = String.format("SELECT Ingredient.Ingredient_ID,Name,Ingredient_in_Recipe.Amount,Ingredient_in_Recipe.Unit\n" +
                "FROM Ingredient inner join Ingredient_in_Recipe\n" +
                "where Ingredient.Ingredient_ID=Ingredient_in_Recipe.Ingredient_ID " +
                "AND Recipe_ID=%d;", recipeID);

        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            while (rs.next()) {
                ingredientID = rs.getInt(1);
                name = rs.getString(2);
                amount = rs.getDouble(3);
                unit = UnitEnum.valueOf(rs.getString(4));
                ingredients.add(new RecipeIngredient(ingredientID, name, amount, unit));
            }
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not fetch recipe ingredients.");
        }
        return ingredients;
    }

    static boolean deleteAll(int recipeID) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("DELETE FROM Ingredient_in_Recipe WHERE Recipe_ID=%d", recipeID);
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("UPDATE Ingredient_in_Recipe SET Amount=%f",
                this.getAmount());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
            return true;
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEnough() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("SELECT * FROM Ingredient WHERE Ingredient_ID=%d", this.getID());
        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            double storageAmount = rs.getDouble(3);
            if (storageAmount < this.getAmount()) {
                return false;
            }
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private int getRecipeID() {
        return recipeID;
    }
}
