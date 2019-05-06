package controller;

import model.*;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientController implements DatabaseController<Ingredient>{
    public IngredientController(){

    }
    public ArrayList<Ingredient> getAll() throws FetchDataException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        String name;
        double amount;
        String unit;
        try {
            ResultSet rs = dbHelper.execSqlWithReturn("SELECT * FROM Ingredient");
            while (rs.next()) {
                name = rs.getString(2);
                amount = rs.getDouble(3);
                unit = rs.getString(4);
                ingredients.add(new Ingredient(name, amount,UnitEnum.valueOf(unit)));
            }
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException | SQLException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not fetch Equipment information.");
        } catch (EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    public boolean insert(Ingredient ingredient) { // Do not use this directly
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("INSERT INTO Ingredient VALUES (1,'%s',%d,'%s')",
                ingredient.getName(), ingredient.getAmount(),String.valueOf(ingredient.getUnit()));
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
