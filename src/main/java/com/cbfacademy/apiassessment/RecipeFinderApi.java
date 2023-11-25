package com.cbfacademy.apiassessment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
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
@ComponentScan("com.cbfacademy.apiassessment")
@RestController
public class RecipeFinderApi {
    private final RecipeService recipeService;

    @Autowired
    public RecipeFinderApi(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RecipeFinderApi.class, args);
    }

    // Search all recipes
    @GetMapping("/recipes")
    public ResponseEntity<String> getRecipes() {
        return recipeService.getRecipes();
    }

    // General search for specific recipe by name
    @GetMapping("/recipes/search")
    public ResponseEntity<String> searchRecipeByName(@RequestParam("name") String recipeName) {
        return recipeService.searchRecipeByName(recipeName);
    }

    // Search for recipes based on specific ingredients
    @GetMapping("/recipes/findByIngredients")
    public ResponseEntity<String> searchByIngredients(@RequestParam("ingredient") List<String> ingredients) {
        return recipeService.searchByIngredients(ingredients);
    }
    //Search recipes by meal type
    @GetMapping("/recipes/findByCategory")
    public ResponseEntity<String> searchByCategory(@RequestParam("mealType") String mealType) {
        return recipeService.searchByCategory(mealType);
    }

    // Search for recipes by cooking method
    @GetMapping("/recipes/findByCookingMethod")
    public ResponseEntity<String> searchByCookingMethod(@RequestParam("method") String cookingMethod) {
        return recipeService.searchByCookingMethod(cookingMethod);
    }

    // Search for recipes by cultural influence
    @GetMapping("/recipes/findByCulturalInfluence")
    public ResponseEntity<String> searchRecipesByCulturalInfluence(@RequestParam("culturalInfluence") String culturalInfluence) {
        return recipeService.searchRecipesByCulturalInfluence(culturalInfluence);
    }

    // Create Recipe - POST
    @PostMapping("/recipes")
    public ResponseEntity<String> createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);

    }

    // Update Recipe - PUT (Update entire recipe)
    @PutMapping("/recipes/{recipeID}")
    public ResponseEntity<String> updateRecipe(@PathVariable int recipeID, @RequestBody Recipe updatedRecipe) {
        return recipeService.updateRecipe(recipeID, updatedRecipe);

    }

    // Update Recipe - PATCH (Update parts of recipe)
    @PatchMapping("/recipes/{recipeID}")
    public ResponseEntity<String> patchRecipe(@PathVariable("recipeID") int recipeID,
            @RequestBody Recipe patchedRecipe) {
        return recipeService.patchRecipe(recipeID, patchedRecipe);
    }

    // Delete Recipe by ID
    @DeleteMapping("/recipes/{recipeID}")
    public ResponseEntity<String> deleteRecipeById(@PathVariable("recipeID") int recipeID) {
        return recipeService.deleteRecipeById(recipeID);

    }
}
