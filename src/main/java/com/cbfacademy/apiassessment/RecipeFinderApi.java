package com.cbfacademy.apiassessment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

// @RestController = @Controller + @ResponseBody; handles HTTP requests and
// returns response. Any method within class treated as web service endpoint,
// return values linked to response body
@RestController
public class RecipeFinderApi {

    public static void main(String[] args) {
        SpringApplication.run(RecipeFinderApi.class, args);
    }

    // @GetMapping = @RequestMapping(value = "/recipes", method =
    // RequestMethod.GET); maps HTTP GET requests(retrieves resources)
    @GetMapping("/recipes")
    public String getRecipes() {
        try {
            ClassPathResource resource = new ClassPathResource("recipes.json");
            byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String jsonData = new String(fileData);

            // Initialize GSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Convert JSON to list of Recipe objects
            List<Recipe> recipes = gson.fromJson(jsonData, new TypeToken<List<Recipe>>() {
            }.getType());

            // Convert the list of recipes back to a formatted JSON string
            String formattedJson = gson.toJson(recipes);

            return formattedJson;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to read recipes.";
        }
    }

    // General search for specific recipe by name
    @GetMapping("/recipes/search")
    public ResponseEntity<String> searchRecipeByName(@RequestParam("name") String recipeName) {
        try {
            ClassPathResource resource = new ClassPathResource("recipes.json");
            byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String jsonData = new String(fileData);

            // Initialize GSON
            Gson gson = new Gson();

            // Convert JSON to list of Recipe objects
            List<Recipe> recipes = gson.fromJson(jsonData, new TypeToken<List<Recipe>>() {
            }.getType());

            // Search for recipes by name
            List<Recipe> matchingRecipes = recipes.stream()
                    .filter(recipe -> recipe.getName().toLowerCase().contains(recipeName.toLowerCase()))
                    .collect(Collectors.toList());

            if (!matchingRecipes.isEmpty()) {
                return new ResponseEntity<>(gson.toJson(matchingRecipes), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No recipes found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving recipes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Search for recipes based on specific ingredients
    @GetMapping("/recipes/findByIngredients")
    public ResponseEntity<String> searchByIngredients(@RequestParam("ingredient") List<String> ingredients) {
        try {
            // Read existing recipes from the JSON file in the resources folder
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Enable pretty printing
            List<Recipe> recipes;
            ClassPathResource resource = new ClassPathResource("recipes.json");
            try (InputStream inputStream = resource.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream)) {
                recipes = gson.fromJson(reader, new TypeToken<List<Recipe>>() {
                }.getType());
            }

            // Search for recipes by any of the provided ingredients (case insensitive)
            List<Recipe> matchingRecipes = recipes.stream()
                    .filter(recipe -> recipeContainsAnyIngredient(recipe, ingredients))
                    .collect(Collectors.toList());

            if (!matchingRecipes.isEmpty()) {
                return new ResponseEntity<>(gson.toJson(matchingRecipes), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No recipes found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving recipes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper method to check if a recipe contains any of the provided ingredients
    private boolean recipeContainsAnyIngredient(Recipe recipe, List<String> ingredients) {
        for (String ingredient : ingredients) {
            if (recipe.getIngredients().contains(ingredient)) {
                return true;
            }
        }
        return false;
    }

    // Search for recipes by category
    @GetMapping("/recipes/findByCategory")
    public ResponseEntity<String> searchByCategory(@RequestParam("mealType") String mealType) {
        try {
            // Read existing recipes from the JSON file
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Enable pretty printing
            List<Recipe> recipes;
            ClassPathResource resource = new ClassPathResource("recipes.json");
            try (InputStream inputStream = resource.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream)) {
                recipes = gson.fromJson(reader, new TypeToken<List<Recipe>>() {
                }.getType());
            }

            // Search for recipes by category
            List<Recipe> matchingRecipes = recipes.stream()
                    .filter(recipe -> recipe.getMealType().equalsIgnoreCase(mealType))
                    .collect(Collectors.toList());

            if (!matchingRecipes.isEmpty()) {
                return new ResponseEntity<>(gson.toJson(matchingRecipes), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No recipes found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving recipes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Search for recipes by cooking method
    @GetMapping("/recipes/findByCookingMethod")
    public ResponseEntity<String> searchByCookingMethod(@RequestParam("method") String cookingMethod) {
        try {
            // Read existing recipes from the JSON file
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Enable pretty printing
            List<Recipe> recipes;
            ClassPathResource resource = new ClassPathResource("recipes.json");
            try (InputStream inputStream = resource.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream)) {
                recipes = gson.fromJson(reader, new TypeToken<List<Recipe>>() {
                }.getType());
            }

            // Search for recipes by cooking method
            List<Recipe> matchingRecipes = recipes.stream()
                    .filter(recipe -> recipe.getCookingMethod().equalsIgnoreCase(cookingMethod))
                    .collect(Collectors.toList());

            if (!matchingRecipes.isEmpty()) {
                return new ResponseEntity<>(gson.toJson(matchingRecipes), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No recipes found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error retrieving recipes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create Recipe - POST

    @PostMapping("/recipes")
    public ResponseEntity<String> createRecipe(@RequestBody Recipe recipe) {
        try {
            Gson gson = new Gson();

            // Read the existing recipes from the JSON file in the resources folder
            List<Recipe> existingRecipes;
            ClassPathResource resource = new ClassPathResource("recipes.json");
            try (InputStream inputStream = resource.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream)) {
                existingRecipes = gson.fromJson(reader, new TypeToken<List<Recipe>>() {
                }.getType());
            }

            // Append the new recipe to the existing list
            existingRecipes.add(recipe);

            // Write the updated list back to the JSON file in the resources folder
            try (FileWriter writer = new FileWriter(resource.getFile())) {
                gson.toJson(existingRecipes, writer);
            }

            // Return a success response
            return new ResponseEntity<>("Recipe created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any errors that occur during the creation process
            return new ResponseEntity<>("Error creating the recipe", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Recipe - PUT (Update entire recipe)

    @PutMapping("/recipes/{recipeID}")
    public ResponseEntity<String> updateRecipe(@PathVariable int recipeID, @RequestBody Recipe updatedRecipe) {
        try {
            List<Recipe> existingRecipes = getAllRecipesFromJson();

            boolean recipeFound = false;
            for (int i = 0; i < existingRecipes.size(); i++) {
                Recipe recipe = existingRecipes.get(i);
                if (recipe.getId() == recipeID) {
                    recipe.setName(updatedRecipe.getName());
                    recipe.setMealType(updatedRecipe.getMealType());
                    recipe.setCookingMethod(updatedRecipe.getCookingMethod());
                    recipe.setIngredients(updatedRecipe.getIngredients());
                    recipe.setInstructions(updatedRecipe.getInstructions());

                    existingRecipes.set(i, recipe);
                    updateJsonFile(existingRecipes);
                    recipeFound = true;
                    break;
                }
            }

            if (recipeFound) {
                return new ResponseEntity<>("Recipe updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Recipe not found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating the recipe", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper method to read recipes from recipes.json in resources folder
    private List<Recipe> getAllRecipesFromJson() throws IOException {
        Gson gson = new Gson();
        ClassPathResource resource = new ClassPathResource("recipes.json");
        try (InputStream inputStream = resource.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream)) {
            return gson.fromJson(reader, new TypeToken<List<Recipe>>() {
            }.getType());
        }
    }

    // Helper method to update recipes.json in resources folder
    private void updateJsonFile(List<Recipe> updatedRecipes) throws IOException {
        Gson gson = new Gson();
        ClassPathResource resource = new ClassPathResource("recipes.json");
        try (FileWriter writer = new FileWriter(resource.getFile())) {
            gson.toJson(updatedRecipes, writer);
        }
    }

    // Update Recipe - PATCH (Update parts of recipe)

    @PatchMapping("/recipes/{recipeID}")
    public ResponseEntity<String> patchRecipe(@PathVariable("recipeID") int recipeID,
            @RequestBody Recipe patchedRecipe) {
        try {
            List<Recipe> existingRecipes = getAllRecipesFromJson(); // Helper method to fetch all recipes

            for (int i = 0; i < existingRecipes.size(); i++) {
                Recipe recipe = existingRecipes.get(i);
                if (recipe.getId() == recipeID) {
                    if (patchedRecipe.getName() != null) {
                        recipe.setName(patchedRecipe.getName());
                    }
                    if (patchedRecipe.getMealType() != null) {
                        recipe.setMealType(patchedRecipe.getMealType());
                    }
                    if (patchedRecipe.getCookingMethod() != null) {
                        recipe.setCookingMethod(patchedRecipe.getCookingMethod());
                    }
                    if (patchedRecipe.getIngredients() != null) {
                        recipe.setIngredients(patchedRecipe.getIngredients());
                    }
                    if (patchedRecipe.getInstructions() != null) {
                        recipe.setInstructions(patchedRecipe.getInstructions());
                    }

                    existingRecipes.set(i, recipe);
                    updateJsonFile(existingRecipes); // Helper method to update JSON file
                    return new ResponseEntity<>("Recipe patched successfully", HttpStatus.OK);
                }
            }

            return new ResponseEntity<>("Recipe not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error patching the recipe", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Recipe by ID
    @DeleteMapping("/recipes/{recipeID}")
    public ResponseEntity<String> deleteRecipeById(@PathVariable("recipeID") int recipeID) {
        try {
            Gson gson = new Gson();

            // Read existing recipes from the JSON file
            ClassPathResource resource = new ClassPathResource("recipes.json");
            List<Recipe> recipes;
            try (InputStream inputStream = resource.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream)) {
                recipes = gson.fromJson(reader, new TypeToken<List<Recipe>>() {
                }.getType());
            }

            // Find the recipe with the given ID and remove it
            boolean recipeRemoved = recipes.removeIf(recipe -> recipe.getId() == recipeID);

            if (recipeRemoved) {
                // Write the updated list back to the JSON file
                try (OutputStream outputStream = new FileOutputStream(resource.getFile());
                        Writer writer = new OutputStreamWriter(outputStream)) {
                    gson.toJson(recipes, writer);
                }
                return new ResponseEntity<>("Recipe deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Recipe not found", HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting the recipe", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
