package com.cbfacademy.apiassessment;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class RecipeService {

    public ResponseEntity<String> getRecipes() {
        try {
            ClassPathResource resource = new ClassPathResource("recipes.json");
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());
    
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
            // Convert JSON to list of Recipe objects
            List<Recipe> recipes = gson.fromJson(reader, new TypeToken<List<Recipe>>() {}.getType());
    
            // Convert the list of recipes back to a formatted JSON string
            String formattedJson = gson.toJson(recipes);
    
            // Close the reader
            reader.close();
    
            return new ResponseEntity<>(formattedJson, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: Unable to read recipes.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    public ResponseEntity<String> searchRecipeByName(String recipeName) {
        try {
            // Read the existing recipes from the JSON file
            Gson gson = new Gson();
            ClassPathResource resource = new ClassPathResource("recipes.json");
            byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String jsonData = new String(fileData);

            // Convert JSON to a list of Recipe objects
            List<Recipe> recipes = gson.fromJson(jsonData, new TypeToken<List<Recipe>>() {
            }.getType());

            // Search for recipes by name
            List<Recipe> matchingRecipes = recipes.stream()
                    .filter(recipe -> recipe.getName().toLowerCase().contains(recipeName.toLowerCase()))
                    .collect(Collectors.toList());

            if (!matchingRecipes.isEmpty()) {
                // Convert the list of matching recipes to JSON string and return as
                // ResponseEntity
                String matchingRecipesJson = gson.toJson(matchingRecipes);
                return new ResponseEntity<>(matchingRecipesJson, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No recipes found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving recipes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
/* 
    public ResponseEntity<String> searchByIngredients(List<String> ingredients) {
        // Your implementation for searching recipes by ingredients
    }

    public ResponseEntity<String> searchByCategory(String mealType) {
        // Your implementation for searching recipes by meal type
    }

    public ResponseEntity<String> searchByCookingMethod(String cookingMethod) {
        // Your implementation for searching recipes by cooking method
    }

    public ResponseEntity<String> createRecipe(Recipe recipe) {
        // Your implementation for creating a recipe
    }

    public ResponseEntity<String> updateRecipe(int recipeID, Recipe updatedRecipe) {
        // Your implementation for updating a recipe
    }

    public ResponseEntity<String> patchRecipe(int recipeID, Recipe patchedRecipe) {
        // Your implementation for patching a recipe
    }

    public ResponseEntity<String> deleteRecipeById(int recipeID) {
        // Your implementation for deleting a recipe by ID
    }

    // Helper methods
    private List<Recipe> getAllRecipesFromJson() {
        // Your implementation to fetch all recipes from JSON
    }

    private void updateJsonFile(List<Recipe> updatedRecipes) {
        // Your implementation to update the JSON file
    }

    private boolean recipeContainsAnyIngredient(Recipe recipe, List<String> ingredients) {
        // Your implementation to check if a recipe contains any of the provided
        // ingredients
    }
}*/