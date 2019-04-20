package test;

import model.EmptyIngredientNameException;
import model.Ingredient;
import model.InvalidIngredientAmountException;
import org.junit.Ignore;
import utils.UnitEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {

    private static Ingredient ingredient;

    @Before
    public void setUp() {
        try {
            ingredient = new Ingredient(1,"yeast", 100.0, UnitEnum.GRAM);
        } catch (EmptyIngredientNameException | InvalidIngredientAmountException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getID() {
        assertEquals(1, ingredient.getID());
    }

    @Test
    public void setID() {
        ingredient.setID(2);
        assertEquals(2, ingredient.getID());
    }

    @Test
    public void getName() {
        assertEquals("yeast", ingredient.getName());
    }

    @Test
    public void setName() {
        try {
            ingredient.setName("water");
        } catch (EmptyIngredientNameException e) {
            e.printStackTrace();
        }
        assertEquals("water", ingredient.getName());
        try {
            ingredient.setName("");
        } catch (EmptyIngredientNameException e) {
            e.printStackTrace();
        }
        assertEquals("water", ingredient.getName());
    }

    @Test
    public void getAmount() {
        assertEquals(100.0, ingredient.getAmount(), 0.00);
    }

    @Ignore("setAmount() is a protected method and cannot test here.")

    @Test
    public void setAmount() {
    }

    @Test
    public void getUnit() {
        assertEquals(UnitEnum.GRAM, ingredient.getUnit());
    }

    @Test
    public void setUnit() {
        ingredient.setUnit(UnitEnum.KILOGRAM);
        assertEquals(UnitEnum.KILOGRAM, ingredient.getUnit());
    }
}