package server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import model.EmptyIngredientNameException;
import model.Ingredient;
import model.InvalidIngredientAmountException;
import utils.UnitEnum;

import java.util.ArrayList;

public class IngredientController {
    private ArrayList<Ingredient> addIngredientTest() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        try {
            ingredients.add(new Ingredient(1, "water", 100.0, UnitEnum.LITER));
            ingredients.add(new Ingredient(2, "yeast", 10, UnitEnum.KILOGRAM));
        } catch (EmptyIngredientNameException | InvalidIngredientAmountException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    protected String getIngredientList() {
        ArrayList<Ingredient> ingredients = addIngredientTest();
        JSONObject ingredientObj = new JSONObject();
        ingredientObj.put("ingredients", ingredients);
        return ingredientObj.toString();
    }
}
