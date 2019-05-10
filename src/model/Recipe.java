package model;

import controller.ModelListener;
import utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Recipe implements IDatabaseOperation<Recipe> {
    private int id;
    private String name;
    private String description;
    private ArrayList<RecipeIngredient> ingredients;
    private ModelListener listener;

    public Recipe() {
        // Nothing to do
    }

    public Recipe(int id, String name, String description) {
        setID(id);
        setName(name);
        setDescription(description);
    }

    public Recipe(String name, String description, ArrayList<RecipeIngredient> ingredients) {
        setName(name);
        setDescription(description);
        setIngredients(ingredients);
    }

    public Recipe(int id, String name, String description, ArrayList<RecipeIngredient> ingredients) {
        setID(id);
        setName(name);
        setDescription(description);
        setIngredients(ingredients);
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void amountConversion(double originalBatchSize, double targetBatchSize) throws InvalidInputException {
        //originalBatchSize should be used mL as unit.This method is to convert all recipeIngredients to the 1L amount.
        try {
            if(originalBatchSize < 0 && originalBatchSize <= Equipment.getEquipment(1).getVolume()){
                throw new InvalidInputException("Batch size could not be equal or less than 0!");
            }
            else{
                for (RecipeIngredient ingredient : this.ingredients) {
                    try {
                        ingredient.setAmount(ingredient.getAmount() * (targetBatchSize / originalBatchSize));
                        if(this.listener != null){
                            this.notifyListener();
                        }
                    } catch (InvalidInputException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FetchDataException | EmptyNameException e) {
            e.printStackTrace();
        }
    }

    public void modifyRecipeIngredient(RecipeIngredient recipeIngredient) throws ModifyObjectException {
        for(int i = 0;i < this.ingredients.size();i++){
            if(this.ingredients.get(i).getName().equals(recipeIngredient.getName())){
                this.ingredients.remove(i);
                this.ingredients.add(i, recipeIngredient);
                return;
            }
        }
        throw new ModifyObjectException("Cannot modify a recipe not existing!");
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredients) throws AddObjectException {
        for (RecipeIngredient ingredient : this.ingredients) {
            if (ingredient == recipeIngredients) {
                throw new AddObjectException(recipeIngredients.getName() + "is already existed!");
            }
        }
        this.ingredients.add(recipeIngredients);
    }

    public boolean isAvailable() {
        for (RecipeIngredient ingredient : ingredients) {
            if (!ingredient.isEnough())
                return false;
        }
        return true;
    }

    private int getNewID() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("SELECT Recipe_ID FROM Recipe WHERE Name='%s'", stringParser(this.getName()));
        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            int newID = rs.getInt(1);
            dbHelper.closeConnection();
            return newID;
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean update() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("UPDATE Recipe SET Name='%s',Description='%s' WHERE Recipe_ID=%d",
                stringParser(this.getName()), stringParser(this.getDescription()), this.getID());
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<Recipe> getAll() throws FetchDataException, EmptyNameException, InvalidInputException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = "SELECT * FROM Recipe";
        int id;
        String name, description;

        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                description = rs.getString(3);
                recipes.add(new Recipe(id, name, description, RecipeIngredient.getAll(id)));
            }
        } catch (SQLException | SQLiteConnectionException e) {
            e.printStackTrace();
            throw new FetchDataException("Fail to fetch recipes.");
        }
        return recipes;
    }

    @Override
    public boolean insert() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        boolean status;
        String query = String.format("INSERT INTO Recipe (Name,Description) VALUES ('%s','%s')",
                stringParser(this.getName()), stringParser(this.getDescription()));
        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        int recipeID = getNewID();
        for (RecipeIngredient ingredient : ingredients) {
            ingredient.setRecipeID(recipeID);
            status = ingredient.insert();
            if (!status)
                return false;
        }
        return true;
    }

    @Override
    public boolean delete() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        boolean status;
        String query = String.format("DELETE FROM Recipe WHERE Recipe_ID=%d", this.getID());

        try {
            dbHelper.execSqlNoReturn(query);
            dbHelper.closeConnection();
        } catch (SQLiteConnectionException e) {
            e.printStackTrace();
            return false;
        }
        for (RecipeIngredient ingredient : ingredients) {
            status = ingredient.delete();
            if (!status)
                return false;
        }
        notifyListener();
        return true;
    }

    @Override
    public void addListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyListener() {
        listener.update();
    }
}
