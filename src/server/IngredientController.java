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

    protected String addIngredient(String jsonString) {
        Ingredient ingredient = JSON.parseObject(jsonString, Ingredient.class);
        JSONObject obj = new JSONObject();
        obj.put("name", ingredient.getName());
        obj.put("amount", ingredient.getAmount());
        obj.put("unit", ingredient.getUnit());
        obj.put("status", true);
        return obj.toString();
    }

    protected String updateIngredient(String jsonString) {
        Ingredient ingredient = JSON.parseObject(jsonString, Ingredient.class);
        JSONObject obj = new JSONObject();
        obj.put("name", ingredient.getName());
        obj.put("amount", ingredient.getAmount());
        obj.put("unit", ingredient.getUnit());
        obj.put("id", ingredient.getID());
        obj.put("status", true);
        return obj.toString();
    }

    protected String deleteIngredient(String request) {
        int id = Integer.parseInt(request.split("=")[1]);
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("status", true);
        return obj.toString();
    }
}
