package com.cbfacademy.apiassessment;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RecipeUtility {

    public static boolean isSpecialRecipe(Recipe recipe) {
        return recipe.getType() != null && recipe.getType().equalsIgnoreCase("special");
    }

    public static SpecialRecipe createSpecialRecipeFromJson(Recipe recipe) {
        int id = recipe.getId();
        String name = recipe.getName();
        String mealType = recipe.getMealType();
        String cookingMethod = recipe.getCookingMethod();
        List<String> ingredients = recipe.getIngredients();
        String instructions = recipe.getInstructions();
        String culturalInfluence = recipe.getCulturalInfluence();
    
        // Create a new SpecialRecipe object using the fields from Recipe
        return new SpecialRecipe(id, name, mealType, cookingMethod, ingredients, instructions, culturalInfluence);
    }
}
