package test;

import model.*;
import org.junit.Before;
import org.junit.Test;
import utils.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RecipeTest {
    // initial part
    private static Recipe recipe;
    ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @Before
    public void setUp() {// set up the recipe
        recipe = new Recipe(1,"beer","good",recipeIngredients);
    }

    @Test
    public void getID() {// test whether it can get ID
        assertEquals(1,recipe.getID());
    }

    @Test
    public void setID() {// test whether it can set ID
        recipe.setID(2);
        assertEquals(2,recipe.getID());
    }

    @Test
    public void getName() {// test whether it can get name
        assertEquals("beer",recipe.getName());
    }

    @Test
    public void setName() {// test whether it can set name
        recipe.setName("badBeer");
        assertEquals("badBeer",recipe.getName());
    }

    @Test
    public void getDescription() {// test whether it get description
        assertEquals("good",recipe.getDescription());
    }

    @Test
    public void setDescription() {// test whether it can set description
        recipe.setDescription("bad");
        assertEquals("bad",recipe.getDescription());
    }

    @Test
    public void getIngredients() {// test whether it can get ingredients
        assertEquals(recipeIngredients,recipe.getIngredients());
    }

    @Test
    public void setIngredients() {// test whether it can set ingredients
        ArrayList<RecipeIngredient> modifyRecipeIngredient = new ArrayList<>();
        recipe.setIngredients(modifyRecipeIngredient);
        assertEquals(modifyRecipeIngredient,recipe.getIngredients());
    }

    @Test
    public void addRecipeIngredient() {// test whether it can add recipe ingredient
        try {
            RecipeIngredient water = new RecipeIngredient(1,"water",100.0, UnitEnum.LITER);
            try {
                recipe.addRecipeIngredient(water);
                assertEquals(water,recipe.getIngredients().get(0));
            } catch (AddObjectException e) {
                e.printStackTrace();
            }
        } catch (EmptyNameException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void amountConversion() {// test whether it can do amount conversion
        try {
            RecipeIngredient water = new RecipeIngredient(1,"water",100.0, UnitEnum.LITER);
            recipe.addRecipeIngredient(water);
            recipe.amountConversion(50, 1000);
            assertEquals(5.0,recipe.getIngredients().get(0).getAmount(),0.0);
        } catch (EmptyNameException | InvalidInputException | AddObjectException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyRecipeIngredient() {// test whether it can modify recipe ingredient
        try {
            RecipeIngredient water = new RecipeIngredient(1,"water",50,UnitEnum.LITER);
            RecipeIngredient modifyWater = new RecipeIngredient(2,"water",50,UnitEnum.LITER);
            recipe.addRecipeIngredient(water);
            recipe.modifyRecipeIngredient(modifyWater);
            assertEquals(2,recipe.getIngredients().get(0).getID());
        } catch (EmptyNameException | ModifyObjectException | AddObjectException | InvalidInputException e) {
            e.printStackTrace();
        }
    }

}