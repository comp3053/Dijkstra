package model;

import controller.ModelListener;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeIngredient extends Ingredient implements IDatabaseOperation {
    private ModelListener listener;
    private int recipeID;

    public RecipeIngredient(int ingredientID, String name, double amount, UnitEnum unit) throws EmptyNameException,
            InvalidInputException {
        super(ingredientID, name, amount, unit);
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
        String query = String.format("INSERT OR IGNORE INTO Ingredient_in_Recipe VALUES (%d,%d,%f,'%s')",
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

    /**
     * Get all the recipe ingredients by recipe ID.
     * @param recipeID ID of recipe that you want to get all the recipe ingredients.
     * @return All the recipe ingredients for the recipe.
     * @throws FetchDataException Throws when database operation fails.
     * @throws EmptyNameException Throws when the recipe's name is empty.
     * @throws InvalidInputException Throws when the amount of recipe ingredient is invalid.
     */
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
            dbHelper.closeConnection();
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not fetch recipe ingredients.");
        }

        return ingredients;
    }

    /**
     * Delete all the recipe ingredient for the recipe.
     * @param recipeID Recipe ID of deleted recipe.
     * @return Whether the delete operation of recipe ingredient is successful.
     */
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

    /**
     * Check if the storage ingredient is enough for a brew according to the requirement of recipe ingredient amount.
     * @param equipmentSize Size of our equipment.
     * @return Whether the storage ingredient is enough for a brew.
     */
    boolean isEnough(int equipmentSize) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("SELECT * FROM Ingredient WHERE Ingredient_ID=%d", this.getID());
        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            double storageAmount = rs.getDouble(3);
            dbHelper.closeConnection();
            if (storageAmount < this.getAmount() / 1000 * equipmentSize) {
                System.out.println(this.getName() + " need extra " + (this.getAmount() / 1000 * equipmentSize - storageAmount) + " " + this.getUnit().toString().toLowerCase());
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
