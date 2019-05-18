package model;

import controller.ModelListener;
import utils.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Recipe implements IDatabaseOperation {
    private int id;
    private String name;
    private String description;
    private ArrayList<RecipeIngredient> ingredients;
    private ModelListener listener;

    public Recipe() {
        // Nothing to do
    }

    public Recipe(int id) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("SELECT * FROM Recipe WHERE Recipe_ID=%d", id);

        // Get recipe from database by recipe id
        try {
            ResultSet rs = dbHelper.execSqlWithReturn(query);
            this.setName(rs.getString(2));
            this.setDescription(rs.getString(3));
            this.setIngredients(RecipeIngredient.getAll(id));
            dbHelper.closeConnection();
        } catch (SQLException | SQLiteConnectionException | FetchDataException | EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
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

    /**
     * Convert all the ingredients in recipe into unit/1L
     * Attention: originalBatchSize should use ml as unit.
     * @param originalBatchSize Batch size you input
     * @param targetBatchSize Default value is 1000
     * @throws InvalidInputException The format of batch size is wrong
     */
    public void amountConversion(int originalBatchSize, int targetBatchSize) throws InvalidInputException {
        try {
            if (originalBatchSize < 0 && originalBatchSize <= Equipment.getEquipment(1).getVolume()) {
                throw new InvalidInputException("Batch size could not be equal or less than 0!");
            } else {
                for (RecipeIngredient ingredient : this.ingredients) {
                    try {
                        ingredient.setAmount(ingredient.getAmount() * (targetBatchSize * 1.0 / originalBatchSize));
                        if (this.listener != null) {
                            this.notifyListener();
                        }
                    } catch (InvalidInputException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Please choose a valid input.");
                        return;
                    }
                }
            }
        } catch (FetchDataException | EmptyNameException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modify the constitute ingredient for current recipe
     * @param recipeIngredient New ingredients for current recipe
     * @throws ModifyObjectException Throws when an object does not exist in database
     */
    public void modifyRecipeIngredient(RecipeIngredient recipeIngredient) throws ModifyObjectException {
        for (int i = 0; i < this.ingredients.size(); i++) {
            if (this.ingredients.get(i).getName().equals(recipeIngredient.getName())) {
                this.ingredients.remove(i);
                this.ingredients.add(i, recipeIngredient);
                return;
            }
        }
        throw new ModifyObjectException("Cannot modify a recipe not existing!");
    }

    /**
     * Add new recipe ingredients into current recipe
     * @param recipeIngredients New recipe ingredients need to added into recipe
     * @throws AddObjectException Throws when the ingredient exists in recipe
     */
    public void addRecipeIngredient(RecipeIngredient recipeIngredients) throws AddObjectException {
        for (RecipeIngredient ingredient : this.ingredients) {
            if (ingredient == recipeIngredients) {
                throw new AddObjectException(recipeIngredients.getName() + "is already existed!");
            }
        }
        this.ingredients.add(recipeIngredients);
    }

    /**
     * Check if the recipe is available according to the amount of storage ingredient amount
     * @param equipmentSize Size of equipment
     * @return Whether the recipe is available for brewing
     */
    public boolean isAvailable(int equipmentSize) {
        for (RecipeIngredient ingredient : ingredients) {
            if (!ingredient.isEnough(equipmentSize))
                return false;
        }

        return true;
    }

    /**
     * Get the ID of current recipe when it has inserted into database just now.
     * @return New ID of current recipe. If could not get new ID, it would return -1.
     */
    private int getNewID() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("SELECT Recipe_ID FROM Recipe WHERE Name='%s'", stringParser(this.getName()));

        // Find new ID in database
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

    @Override
    public boolean update() {
        DatabaseHelper dbHelper = new DatabaseHelper();
        String query = String.format("UPDATE Recipe SET Name='%s',Description='%s' WHERE Recipe_ID=%d",
                stringParser(this.getName()), stringParser(this.getDescription()), this.getID());

        // Update information of recipe in database.
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
     * Obtain all the recipes in database
     * @return An ArrayList of all the recipes.
     * @throws FetchDataException Throws when fail to get information from database.
     * @throws EmptyNameException Throws when the name of current recipe is empty.
     * @throws InvalidInputException Throws
     */
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
            dbHelper.closeConnection();
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

        status = RecipeIngredient.deleteAll(this.getID());
        notifyListener();
        return status;
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
