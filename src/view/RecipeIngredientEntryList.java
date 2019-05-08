package view;
import model.*;
import utils.EmptyNameException;
import utils.InvalidInputException;

import java.util.ArrayList;
import javax.swing.*;


public class RecipeIngredientEntryList extends JPanel{
    private ArrayList<RecipeIngredientEntry> entries;
    // Replace with your database stuff
    private ArrayList<StorageIngredient> ingredients;
    private ArrayList<String> ingredientNames;

    public RecipeIngredientEntryList(ArrayList<StorageIngredient> ingredients) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.entries = new ArrayList<>();

        this.ingredients = ingredients;
        this.ingredientNames = new ArrayList<>();
        for (StorageIngredient ingredient: ingredients){
            ingredientNames.add(ingredient.getName() + " (Unit: " + ingredient.getUnit().toString().toLowerCase() + ")");
        }
    }

    public void cloneEntry(RecipeIngredientEntry entry) {
        Object selected = entry.getIngredientSelector().getSelectedItem();
        JComboBox copy = new JComboBox(this.ingredientNames.toArray());
        copy.setSelectedItem(selected);
        RecipeIngredientEntry theClone = new RecipeIngredientEntry(copy, "", this);

        addItem(theClone);
    }

    void initForm() {
        RecipeIngredientEntry initial = new RecipeIngredientEntry(new JComboBox(this.ingredientNames.toArray()), "", this);
        addItem(initial);
    }

    void initIngredients(ArrayList<RecipeIngredient> ingredients) {
        for (RecipeIngredient ingredient : ingredients) {
            RecipeIngredientEntry entry = new RecipeIngredientEntry(new JComboBox(this.ingredientNames.toArray()),
                    Double.toString(ingredient.getAmount()), this);
            entry.getIngredientSelector().setSelectedIndex(ingredient.getID() - 1);
            addItem(entry);
        }
    }

    private void addItem(RecipeIngredientEntry entry) {
        this.entries.add(entry);
        add(entry);
        refresh();
    }

    public void removeItem(RecipeIngredientEntry entry) {
        entries.remove(entry);
        remove(entry);
        refresh();
    }

    private void refresh() {
        revalidate();
        if (entries.size() < this.ingredients.size()){
            if (entries.size() == 1) {
                entries.get(0).enableMinus(false);
            }
            else {
                for (RecipeIngredientEntry e : entries) {
                    e.enableMinus(true);
                }
            }
            for (RecipeIngredientEntry e : entries) {
                e.enableAdd(true);
            }
        }else{
            if (entries.size() == 1) {
                entries.get(0).enableMinus(false);
            }
            else {
                for (RecipeIngredientEntry e : entries) {
                    e.enableMinus(true);
                }
            }
            for (RecipeIngredientEntry e : entries) {
                e.enableAdd(false);
            }
        }

    }
    public ArrayList<RecipeIngredient> getIngredientList(){
        ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
        for (RecipeIngredientEntry entrie: entries){
            StorageIngredient currentStorageIngredient = this.ingredients.get(entrie.getIngredientSelector().getSelectedIndex());
            try {
                recipeIngredients.add(
                        new RecipeIngredient(currentStorageIngredient.getID(), currentStorageIngredient.getName(),
                                new Double(entrie.getInputBoxText().getText()),
                                currentStorageIngredient.getUnit())
            );
            }catch (EmptyNameException | InvalidInputException e){
                e.printStackTrace();
            }
        }

        return recipeIngredients;
    }

}