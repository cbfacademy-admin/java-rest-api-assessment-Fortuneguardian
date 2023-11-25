package com.cbfacademy.apiassessment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component

public class DataAccess {
    private final String JSON_FILE_PATH = "recipes.json";

    public boolean recipeContainsAnyIngredient(Recipe recipe, List<String> ingredients) {
        List<String> recipeIngredients = recipe.getIngredients();
        for (String ingredient : ingredients) {
            if (recipeIngredients.contains(ingredient)) {
                return true;
            }
        }
        return false;
    }

    public List<Recipe> getAllRecipesFromJson() throws IOException {
        Gson gson = new Gson();
        ClassPathResource resource = new ClassPathResource(JSON_FILE_PATH);
        List<Recipe> recipes;
        try (InputStream inputStream = resource.getInputStream();
             InputStreamReader reader = new InputStreamReader(inputStream)) {
            recipes = gson.fromJson(reader, new TypeToken<List<Recipe>>() {}.getType());
        }
    
        List<Recipe> updatedRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (RecipeUtility.isSpecialRecipe(recipe)) {
                SpecialRecipe specialRecipe = RecipeUtility.createSpecialRecipeFromJson(recipe);
                updatedRecipes.add(specialRecipe);
            } else {
                updatedRecipes.add(recipe); 
            }
        }
        return updatedRecipes;
    }

    public void updateJsonFile(List<Recipe> updatedRecipes) {
        Gson gson = new Gson();
        ClassPathResource resource = new ClassPathResource(JSON_FILE_PATH);
        try (FileWriter writer = new FileWriter(resource.getFile())) {
            gson.toJson(updatedRecipes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
