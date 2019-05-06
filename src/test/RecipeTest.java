package test;

import model.*;
import org.junit.Before;
import org.junit.Test;
import utils.UnitEnum;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RecipeTest {

    private static Recipe recipe;
    ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<RecipeIngredient>();

    @Before
    public void setUp() throws Exception {
        recipe = new Recipe(1,"beer","good",recipeIngredients);
    }

    @Test
    public void getID() {
        assertEquals(1,recipe.getID());
    }

    @Test
    public void setID() {
        recipe.setID(2);
        assertEquals(2,recipe.getID());
    }

    @Test
    public void getName() {
        assertEquals("beer",recipe.getName());
    }

    @Test
    public void setName() {
        recipe.setName("badBeer");
        assertEquals("badBeer",recipe.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("good",recipe.getDescription());
    }

    @Test
    public void setDescription() {
        recipe.setDescription("bad");
        assertEquals("bad",recipe.getDescription());
    }

    @Test
    public void getIngredients() {
        assertEquals(recipeIngredients,recipe.getIngredients());
    }

    @Test
    public void setIngredients() {
        ArrayList<RecipeIngredient> modifyRecipeIngredient = new ArrayList<RecipeIngredient>();
        recipe.setIngredients(modifyRecipeIngredient);
        assertEquals(modifyRecipeIngredient,recipe.getIngredients());
    }

    @Test
    public void addRecipeIngredient() {
        try {
            RecipeIngredient water = new RecipeIngredient(1,"water",100.0, UnitEnum.LITER);
            try {
                recipe.addRecipeIngredient(water);
                assertEquals(water,recipe.getIngredients().get(0));
            } catch (AddExisitingRecipeIngredientsException e) {
                e.printStackTrace();
            }
        } catch (EmptyIngredientNameException e) {
            e.printStackTrace();
        } catch (InvalidIngredientAmountException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void amountConversion() {
        try {
            RecipeIngredient water = new RecipeIngredient(1,"water",100.0, UnitEnum.LITER);
            recipe.addRecipeIngredient(water);
            recipe.amountConversion(50.0);
            assertEquals(2.0,recipe.getIngredients().get(0).getAmount(),0.0);
        } catch (EmptyIngredientNameException e) {
            e.printStackTrace();
        } catch (InvalidIngredientAmountException e) {
            e.printStackTrace();
        } catch (AddExisitingRecipeIngredientsException e) {
            e.printStackTrace();
        } catch (InvalidOriginalBatchSizeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyRecipeIngredient() {
        try {
            RecipeIngredient water = new RecipeIngredient(1,"water",50,UnitEnum.LITER);
            RecipeIngredient modifyWater = new RecipeIngredient(2,"water",50,UnitEnum.LITER);
            recipe.addRecipeIngredient(water);
            recipe.modifyRecipeIngredient(modifyWater);
            assertEquals(2,recipe.getIngredients().get(0).getID());
        } catch (EmptyIngredientNameException e) {
            e.printStackTrace();
        } catch (InvalidIngredientAmountException e) {
            e.printStackTrace();
        } catch (AddExisitingRecipeIngredientsException e) {
            e.printStackTrace();
        } catch (ModifyNotExisitingRecipeIngredientException e) {
            e.printStackTrace();
        }
    }

}