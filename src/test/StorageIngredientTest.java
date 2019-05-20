package test;

import utils.EmptyNameException;
import utils.InvalidInputException;
import model.StorageIngredient;
import org.junit.Before;
import org.junit.Test;
import utils.UnitEnum;

import static org.junit.Assert.*;

public class StorageIngredientTest {
    //initial part
    private static StorageIngredient storageIngredient;

    @Before
    public void setUp() {//set up the storage ingredient
        try {
            storageIngredient = new StorageIngredient(1, "wheat", 5.0, UnitEnum.KILOGRAM);
        } catch (EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addAmount() {// test whether it can add amount
        try {
            storageIngredient.addAmount(5.0);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        assertEquals(10.0, storageIngredient.getAmount(), 0.00);

        try {
            storageIngredient.addAmount(-10.0);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        assertEquals(10.0, storageIngredient.getAmount(), 0.00);
    }
}