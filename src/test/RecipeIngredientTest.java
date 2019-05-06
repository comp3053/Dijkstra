package test;

import model.RecipeIngredient;
import model.EmptyIngredientNameException;
import model.InvalidIngredientAmountException;
import org.junit.Before;
import org.junit.Test;
import utils.UnitEnum;

import static org.junit.Assert.*;

public class RecipeIngredientTest {

    private  static RecipeIngredient recipeIngredient;

    @Before
    public void setUp() throws Exception {
        try {
            recipeIngredient = new RecipeIngredient(1, "wheat", 5.0, UnitEnum.KILOGRAM);
        } catch (EmptyIngredientNameException | InvalidIngredientAmountException e) {
            e.printStackTrace();
        }
    }
}