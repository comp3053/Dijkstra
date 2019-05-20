package test;

import utils.EmptyNameException;
import model.Ingredient;
import utils.InvalidInputException;
import org.junit.Ignore;
import utils.UnitEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {
    //initial part
    private static Ingredient ingredient;

    @Before
    public void setUp() {//set up the ingredient class
        try {
            ingredient = new Ingredient(1, "yeast", 100.0, UnitEnum.GRAM);
        } catch (EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getID() {// test whether it can get ID
        assertEquals(1, ingredient.getID());
    }

    @Test
    public void setID() {// test whether it can set ID
        ingredient.setID(2);
        assertEquals(2, ingredient.getID());
    }

    @Test
    public void getName() {// test whether it can get name
        assertEquals("yeast", ingredient.getName());
    }

    @Test
    public void setName() {// test whether it can set name
        try {
            ingredient.setName("water");
        } catch (EmptyNameException e) {
            e.printStackTrace();
        }
        assertEquals("water", ingredient.getName());
        try {
            ingredient.setName("");
        } catch (EmptyNameException e) {
            e.printStackTrace();
        }
        assertEquals("water", ingredient.getName());
    }

    @Test
    public void getAmount() {// test whether it can get amount
        assertEquals(100.0, ingredient.getAmount(), 0.00);
    }

    @Ignore("setAmount() is a protected method and cannot test here.")
    @Test
    public void setAmount() {
    }

    @Test
    public void getUnit() {// test whether it can get the unit
        assertEquals(UnitEnum.GRAM, ingredient.getUnit());
    }

    @Test
    public void setUnit() {// test whether it can set the unit
        ingredient.setUnit(UnitEnum.KILOGRAM);
        assertEquals(UnitEnum.KILOGRAM, ingredient.getUnit());
    }
}