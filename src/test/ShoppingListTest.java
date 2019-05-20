package test;

import utils.EmptyNameException;
import model.Ingredient;
import utils.InvalidInputException;
import model.ShoppingList;
import org.junit.Before;
import org.junit.Test;
import utils.UnitEnum;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShoppingListTest {
    //initial part
    private static ShoppingList shoppingList;
    ArrayList<Ingredient> missingIngredients = new ArrayList<>();

    @Before
    public void setUp() {// set up the shopping list
        shoppingList = new ShoppingList(missingIngredients);
    }

    @Test
    public void calculateMissingIngredientAmount() {// test whether it can calculate missing ingredient amount
        assertEquals(2.0,shoppingList.calculateMissingIngredientAmount(3.0,5.0),0.01);
    }

    @Test
    public void getMissingIngredients(){// test whether it can get missing ingredient
        assertEquals(missingIngredients,shoppingList.getMissingIngredients());
    }

    @Test
    public void addMissingIngredient() {// test whether it can add missing ingredient
        Ingredient ingredient;
        try {
            ingredient = new Ingredient(1, "yeast", 100.0, UnitEnum.GRAM);
            shoppingList.addMissingIngredient(ingredient);
            assertEquals(ingredient,shoppingList.getMissingIngredients().get(0));
        } catch (EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}