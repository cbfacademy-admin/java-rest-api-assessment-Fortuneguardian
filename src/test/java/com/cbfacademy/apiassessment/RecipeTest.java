package com.cbfacademy.apiassessment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Basic Test Suite")
public class RecipeTest {

    @Test
    @DisplayName("Verify all the getter methods for a recipe return the correct values")
    public void testRecipeGetters() {
        // Create sample data
        int id = 1;
        String name = "Pasta";
        String mealType = "Italian";
        String cookingMethod = "Boiling";
        List<String> ingredients = Arrays.asList("Pasta", "Water", "Salt");
        String instructions = "Cook pasta in boiling water";
        String culturalInfluence = "Italian";
        String type = "Normal";

        // Create a recipe instance
        Recipe recipe = new Recipe(id, name, mealType, cookingMethod, ingredients, instructions, culturalInfluence,
                type);

        // Test the getters
        assertEquals(id, recipe.getId());
        assertEquals(name, recipe.getName());
        assertEquals(mealType, recipe.getMealType());
        assertEquals(cookingMethod, recipe.getCookingMethod());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(instructions, recipe.getInstructions());
        assertEquals(culturalInfluence, recipe.getCulturalInfluence());
        assertEquals(type, recipe.getType());
    }

    @Test
    @DisplayName("Verify all the setter methods for a recipe return the correct values")
    public void testRecipeSetters() {
        // Create a recipe instance
        Recipe recipe = new Recipe(0, "", "", "", null, "", "", "");

        // Set sample data using setters
        int id = 1;
        String name = "Pasta";
        String mealType = "Italian";
        String cookingMethod = "Boiling";
        List<String> ingredients = Arrays.asList("Pasta", "Water", "Salt");
        String instructions = "Cook pasta in boiling water";
        String culturalInfluence = "Italian";
        String type = "Normal";

        recipe.setId(id);
        recipe.setName(name);
        recipe.setMealType(mealType);
        recipe.setCookingMethod(cookingMethod);
        recipe.setIngredients(ingredients);
        recipe.setInstructions(instructions);
        recipe.setCulturalInfluence(culturalInfluence);
        recipe.setType(type);

        // Test the getters again after using setters
        assertEquals(id, recipe.getId());
        assertEquals(name, recipe.getName());
        assertEquals(mealType, recipe.getMealType());
        assertEquals(cookingMethod, recipe.getCookingMethod());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(instructions, recipe.getInstructions());
        assertEquals(culturalInfluence, recipe.getCulturalInfluence());
        assertEquals(type, recipe.getType());
    }
}
