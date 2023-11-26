package com.cbfacademy.apiassessment;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

public class ExceptionHandling {
    public static ResponseEntity<String> handleSuccessResponse(String formattedJson) {
        return new ResponseEntity<>(formattedJson, HttpStatus.OK);
    }

    // IO Exceptions
    public static ResponseEntity<String> handleIOException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error: Unable to read recipes.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Recipe not found related exceptions
    public static ResponseEntity<String> handleRecipeNotFound() {
        return new ResponseEntity<>("Recipe not found", HttpStatus.NOT_FOUND);
    }

    // Recipe creation related exceptions
    public static ResponseEntity<String> handleRecipeCreationError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error creating the recipe", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<String> handleRecipeCreatedSuccessfully() {
        return new ResponseEntity<>("Recipe created successfully", HttpStatus.CREATED);
    }

    // Recipe retrieval related exceptions
    public static ResponseEntity<String> handleRecipeRetrievalError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error retrieving recipes", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Recipe update related exceptions
    public static ResponseEntity<String> handleRecipeUpdateError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error updating recipe", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<String> handleRecipeUpdatedSuccessfully() {
        return new ResponseEntity<>("Recipe updated successfully", HttpStatus.OK);
    }

    // Recipe patch related exceptions
    public static ResponseEntity<String> handleRecipePatchError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error patching the recipe", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<String> handleRecipePatchedSuccessfully() {
        return new ResponseEntity<>("Recipe patched successfully", HttpStatus.OK);
    }

    // Recipe deletion related exceptions
    public static ResponseEntity<String> handleRecipeDeleteError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error deleting the recipe", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<String> handleRecipeDeletedSuccessfully() {
        return new ResponseEntity<>("Recipe deleted successfully", HttpStatus.OK);
    }

    // Handling matching recipes
    public static ResponseEntity<String> handleMatchingRecipes(List<Recipe> matchingRecipes, Gson gson) {
        if (!matchingRecipes.isEmpty()) {
            String matchingRecipesJson = gson.toJson(matchingRecipes);
            return new ResponseEntity<>(matchingRecipesJson, HttpStatus.OK);
        } else {
            return handleRecipeNotFound();
        }
    }

}
