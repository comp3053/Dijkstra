package view;

import model.*;
import utils.DuplicateObjectException;
import utils.EmptyNameException;
import utils.InvalidInputException;

import java.util.ArrayList;
import javax.swing.*;


class RecipeIngredientEntryList extends JPanel {
    private ArrayList<RecipeIngredientEntry> entries;
    // Replace with your database stuff
    private ArrayList<StorageIngredient> ingredients;
    private ArrayList<String> ingredientNames;

    /**
     * Create a RecipeIngredientList to manage ingredients in recipe.
     * @param ingredients All the ingredients in the recipe.
     */
    public RecipeIngredientEntryList(ArrayList<StorageIngredient> ingredients) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.entries = new ArrayList<>();

        this.ingredients = ingredients;
        this.ingredientNames = new ArrayList<>();
        for (StorageIngredient ingredient : ingredients) {
            ingredientNames.add(ingredient.getName() + " (Unit: " + ingredient.getUnit().toString().toLowerCase() + ")");
        }
    }

    /**
     * Add a new entry for ingredient.
     * @param entry A new RecipeIngredientEntry object.
     */
    void cloneEntry(RecipeIngredientEntry entry) {
        Object selected = entry.getIngredientSelector().getSelectedItem();
        JComboBox copy = new JComboBox(this.ingredientNames.toArray());
        copy.setSelectedItem(selected);
        RecipeIngredientEntry theClone = new RecipeIngredientEntry(copy, "", this);

        addItem(theClone);
    }

    /**
     * Initialize a new form if the recipe is new.
     */
    void initForm() {
        RecipeIngredientEntry initial = new RecipeIngredientEntry(new JComboBox(this.ingredientNames.toArray()), "", this);
        addItem(initial);
    }

    /**
     * Initialize the form with exist ingredients in recipe.
     * @param ingredients Exist ingredients in recipe.
     */
    void initIngredients(ArrayList<RecipeIngredient> ingredients) {
        for (RecipeIngredient ingredient : ingredients) {
            RecipeIngredientEntry entry = new RecipeIngredientEntry(new JComboBox(this.ingredientNames.toArray()),
                    Double.toString(ingredient.getAmount()), this);
            entry.getIngredientSelector().setSelectedIndex(ingredient.getID() - 1);
            addItem(entry);
        }
    }

    /**
     * Add an entry to the EntryList
     * @param entry The entry you want to add to current EntryList
     */
    private void addItem(RecipeIngredientEntry entry) {
        this.entries.add(entry);
        add(entry);
        refresh();
    }

    /**
     * Remove an entry from the EntryList.
     * @param entry Entry you want to remove from the EntryList.
     */
    void removeItem(RecipeIngredientEntry entry) {
        entries.remove(entry);
        remove(entry);
        refresh();
    }

    /**
     * Refresh user interface when EntryList changes.
     */
    private void refresh() {
        revalidate();
        if (entries.size() < this.ingredients.size()) {
            if (entries.size() == 1) {
                entries.get(0).enableMinus(false);
            } else {
                for (RecipeIngredientEntry e : entries) {
                    e.enableMinus(true);
                }
            }
            for (RecipeIngredientEntry e : entries) {
                e.enableAdd(true);
            }
        } else {
            if (entries.size() == 1) {
                entries.get(0).enableMinus(false);
            } else {
                for (RecipeIngredientEntry e : entries) {
                    e.enableMinus(true);
                }
            }
            for (RecipeIngredientEntry e : entries) {
                e.enableAdd(false);
            }
        }

    }

    /**
     * Check whether the ingredient is duplicate in current recipe.
     * @param current Ingredient you selected in current entry.
     * @param ingredients Ingredients in current recipe.
     * @return Whether the ingredient is duplicate in recipe.
     */
    private boolean isDuplicate(StorageIngredient current, ArrayList<RecipeIngredient> ingredients) {
        for (RecipeIngredient ingredient : ingredients) {
            if (ingredient.getName().equals(current.getName()))
                return true;
        }
        return false;
    }

    /**
     * Get all the ingredients you have added in the form.
     * @return An ArrayList of all the ingredients in your form.
     * @throws NumberFormatException The format of amount is invalid.
     * @throws DuplicateObjectException Some ingredients in the form are duplicated.
     * @throws InvalidInputException The amount of ingredient is invalid.
     */
    ArrayList<RecipeIngredient> getIngredientList() throws NumberFormatException, DuplicateObjectException, InvalidInputException {
        ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
        for (RecipeIngredientEntry entry : entries) {
            StorageIngredient currentStorageIngredient = this.ingredients.get(entry.getIngredientSelector().getSelectedIndex());
            // Check if the information of ingredients are valid.
            try {
                if (isDuplicate(currentStorageIngredient, recipeIngredients))
                    throw new DuplicateObjectException("Some ingredients in recipe is duplicated.");
                if (new Double(entry.getInputBoxText().getText()) <= 0)
                    throw new InvalidInputException("Ingredient amount should be more than 0.");
                recipeIngredients.add(
                        new RecipeIngredient(currentStorageIngredient.getID(), currentStorageIngredient.getName(),
                                new Double(entry.getInputBoxText().getText()),
                                currentStorageIngredient.getUnit()));
            } catch (EmptyNameException e) {
                e.printStackTrace();
            }
        }

        return recipeIngredients;
    }

}