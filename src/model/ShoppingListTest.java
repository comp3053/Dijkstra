package model;

import org.junit.Before;
import org.junit.Test;
import utils.UnitEnum;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShoppingListTest {

    private static ShoppingList shoppingList;
    ArrayList<Ingredient> missingIngredients = new ArrayList<Ingredient>();

    @Before
    public void setUp() throws Exception {
        shoppingList = new ShoppingList(missingIngredients);
    }

    @Test
    public void calculateMissingIngredientAmount() {
        assertEquals(1.0,shoppingList.calculateMissingIngredientAmount(1.0,2.0));
    }

    @Test
    public void addMissingIngredient() {
        Ingredient ingredient;
        try {
            ingredient = new Ingredient(1, "yeast", 100.0, UnitEnum.GRAM);
            shoppingList.addMissingIngredient(ingredient);
            assertEquals(ingredient,shoppingList.getMissingIngredients().get(0));
        } catch (EmptyIngredientNameException | InvalidIngredientAmountException e) {
            e.printStackTrace();
        }
    }
}