package utils;

import model.Recipe;

import java.util.Comparator;

public class CustomRecipeComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe r1, Recipe r2) {
        return r2.getIngredients().size() - r1.getIngredients().size();
    }
}
