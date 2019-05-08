package model;

import controller.ModelListener;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StorageIngredient extends Ingredient implements IDatabaseOperation<StorageIngredient> {
    private ModelListener listener;


    public StorageIngredient() {
        // Nothing to do
    }

    public StorageIngredient(int id, String name, double amount, UnitEnum unit) throws EmptyNameException,
            InvalidInputException {
        super(id, name, amount, unit);
    }

    @Override
    public void addListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyListener() {
        this.listener.update();
    }

    public void addAmount(double addition_amount) throws InvalidInputException {
        if (addition_amount <= 0) {
            throw new InvalidInputException("Addition amount should be greater than 0!");
        } else {
            this.setAmount(this.getAmount() + addition_amount);
        }
    }

    @Override
    public boolean insert() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("INSERT INTO Ingredient (Name, Amount, Unit) VALUES ('%s',%f,'%s')",
                stringParser(this.getName()), this.getAmount(), this.getUnit().toString());
        System.out.println(query);
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("UPDATE Ingredient SET Name='%s',Amount=%f,Unit='%s' WHERE Ingredient_ID=%d",
                stringParser(this.getName()), this.getAmount(), this.getUnit().toString(), this.getID());
        System.out.println(query);

        try {
            dbHelper.execSqlUpdate(query);
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
        String query = String.format("DELETE FROM Ingredient WHERE Ingredient_ID=%d", this.getID());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        notifyListener();
        return true;
    }

    public static ArrayList<StorageIngredient> getAll() throws FetchDataException {
        DatabaseHelper dbHelper = new DatabaseHelper();
        ArrayList<StorageIngredient> ingredients = new ArrayList<>();
        String name, unit;
        double amount;
        int id;

        try {
            ResultSet rs = dbHelper.execSqlWithReturn("SELECT * FROM Ingredient");
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                amount = rs.getDouble(3);
                unit = rs.getString(4);
                ingredients.add(new StorageIngredient(id, name, amount, UnitEnum.valueOf(unit)));
            }
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Could not fetch Storage Ingredients.");
        } catch (EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
        return ingredients;
    }
}
