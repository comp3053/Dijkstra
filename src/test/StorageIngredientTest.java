package test;

import utils.EmptyIngredientNameException;
import utils.InvalidIngredientAmountException;
import model.StorageIngredient;
import org.junit.Before;
import org.junit.Test;
import utils.UnitEnum;

import static org.junit.Assert.*;

public class StorageIngredientTest {

    private static StorageIngredient storageIngredient;

    @Before
    public void setUp() {
        try {
            storageIngredient = new StorageIngredient(1, "wheat", 5.0, UnitEnum.KILOGRAM);
        } catch (EmptyIngredientNameException | InvalidIngredientAmountException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addAmount() {
        try {
            storageIngredient.addAmount(5.0);
        } catch (InvalidIngredientAmountException e) {
            e.printStackTrace();
        }
        assertEquals(10.0, storageIngredient.getAmount(), 0.00);

        try {
            storageIngredient.addAmount(-10.0);
        } catch (InvalidIngredientAmountException e) {
            e.printStackTrace();
        }
        assertEquals(10.0, storageIngredient.getAmount(), 0.00);
    }
}