package com.cbfacademy.apiassessment;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class RecipeService {
    private final DataAccess dataAccess;

    public RecipeService(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public ResponseEntity<String> getRecipes() {
        try {

            List<Recipe> recipes = dataAccess.getAllRecipesFromJson();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Convert the list of recipes back to a formatted JSON string
            String formattedJson = gson.toJson(recipes);
            return ExceptionHandling.handleSuccessResponse(formattedJson);
        } catch (IOException e) {
            return ExceptionHandling.handleIOException(e);
        }
    }

    public ResponseEntity<String> searchRecipeByName(String recipeName) {
        try {
            List<Recipe> recipes = dataAccess.getAllRecipesFromJson();
            Gson gson = new Gson();

            // Search for recipes by name
            List<Recipe> matchingRecipes = recipes.stream()
                    .filter(recipe -> recipe.getName().toLowerCase().contains(recipeName.toLowerCase()))
                    .collect(Collectors.toList());

            return ExceptionHandling.handleMatchingRecipes(matchingRecipes, gson);
        } catch (IOException e) {
            return ExceptionHandling.handleRecipeRetrievalError(e);
        }
    }

    public ResponseEntity<String> searchByIngredients(List<String> ingredients) {
        try {
            List<Recipe> recipes = dataAccess.getAllRecipesFromJson();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Search for recipes by any of the provided ingredients (case insensitive)
            List<Recipe> matchingRecipes = recipes.stream()
                    .filter(recipe -> dataAccess.recipeContainsAnyIngredient(recipe, ingredients))
                    .collect(Collectors.toList());

            return ExceptionHandling.handleMatchingRecipes(matchingRecipes, gson);
        } catch (IOException e) {
            return ExceptionHandling.handleRecipeRetrievalError(e);
        }
    }

    public ResponseEntity<String> searchByCategory(String mealType) {
        try {
            List<Recipe> recipes = dataAccess.getAllRecipesFromJson();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<Recipe> matchingRecipes = recipes.stream()
                    .filter(recipe -> recipe.getMealType().equalsIgnoreCase(mealType))
                    .collect(Collectors.toList());

            return ExceptionHandling.handleMatchingRecipes(matchingRecipes, gson);
        } catch (IOException e) {
            return ExceptionHandling.handleRecipeRetrievalError(e);
        }
    }

    public ResponseEntity<String> searchByCookingMethod(String cookingMethod) {
        try {
            List<Recipe> recipes = dataAccess.getAllRecipesFromJson();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<Recipe> matchingRecipes = recipes.stream()
                    .filter(recipe -> recipe.getCookingMethod().equalsIgnoreCase(cookingMethod))
                    .collect(Collectors.toList());

            return ExceptionHandling.handleMatchingRecipes(matchingRecipes, gson);
        } catch (IOException e) {
            return ExceptionHandling.handleRecipeRetrievalError(e);
        }
    }

    public ResponseEntity<String> searchRecipesByCulturalInfluence(String culturalInfluence) {
        try {
            List<Recipe> recipes = dataAccess.getAllRecipesFromJson();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
            List<Recipe> matchingSpecialRecipes = recipes.stream()
                    .filter(recipe -> recipe instanceof SpecialRecipe &&
                            ((SpecialRecipe) recipe).getCulturalInfluence().equalsIgnoreCase(culturalInfluence))
                    .collect(Collectors.toList());
    
            return ExceptionHandling.handleMatchingRecipes(matchingSpecialRecipes, gson);
        } catch (IOException e) {
            return ExceptionHandling.handleRecipeRetrievalError(e);
        }
    }
    

    public ResponseEntity<String> createRecipe(Recipe recipe) {
        try {
            List<Recipe> existingRecipes = dataAccess.getAllRecipesFromJson();

            // Append the new recipe to the existing list
            existingRecipes.add(recipe);

            // Write the updated list back to the JSON file in the resources folder
            dataAccess.updateJsonFile(existingRecipes);

            return ExceptionHandling.handleRecipeCreatedSuccessfully();
        } catch (Exception e) {
            return ExceptionHandling.handleRecipeCreationError(e);
        }
    }

    public ResponseEntity<String> updateRecipe(int recipeID, Recipe updatedRecipe) {
        try {
            List<Recipe> existingRecipes = dataAccess.getAllRecipesFromJson();

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
                    dataAccess.updateJsonFile(existingRecipes);
                    recipeFound = true;
                    break;
                }
            }

            if (recipeFound) {
                return ExceptionHandling.handleRecipeUpdatedSuccessfully();
            } else {
                return ExceptionHandling.handleRecipeNotFound();
            }
        } catch (IOException e) {
            return ExceptionHandling.handleRecipeUpdateError(e);
        }
    }

    public ResponseEntity<String> patchRecipe(int recipeID, Recipe patchedRecipe) {
        try {
            List<Recipe> existingRecipes = dataAccess.getAllRecipesFromJson();

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
                    dataAccess.updateJsonFile(existingRecipes);
                    return ExceptionHandling.handleRecipePatchedSuccessfully();
                }
            }

            return ExceptionHandling.handleRecipeNotFound();
        } catch (IOException e) {
            return ExceptionHandling.handleRecipePatchError(e);
        }
    }

    public ResponseEntity<String> deleteRecipeById(int recipeID) {
        try {
            List<Recipe> existingRecipes = dataAccess.getAllRecipesFromJson();

            boolean recipeRemoved = existingRecipes.removeIf(recipe -> recipe.getId() == recipeID);

            if (recipeRemoved) {
                dataAccess.updateJsonFile(existingRecipes);
                return ExceptionHandling.handleRecipeDeletedSuccessfully();
            } else {
                return ExceptionHandling.handleRecipeNotFound();
            }
        } catch (IOException e) {
            return ExceptionHandling.handleRecipeDeleteError(e);
        }
    }

}