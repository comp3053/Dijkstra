package test;

import model.RecipeIngredient;
import utils.EmptyNameException;
import utils.InvalidInputException;
import org.junit.Before;
import utils.UnitEnum;

public class RecipeIngredientTest {

    private  static RecipeIngredient recipeIngredient;

    @Before
    public void setUp() throws Exception {
        try {
            recipeIngredient = new RecipeIngredient(1, "wheat", 5.0, UnitEnum.KILOGRAM);
        } catch (EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}