package com.cbfacademy.apiassessment;
import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.FileReader;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RecipeFinderApi {

	public static void main(String[] args) {
		SpringApplication.run(RecipeFinderApi.class, args);
	}

	@GetMapping("/recipes")
    public String getRecipes() {
        // Initialize a GSON instance
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("recipes.json")) {
            // Use GSON to deserialize the JSON file into your Java objects
            Recipes[] recipes = gson.fromJson(reader, Recipes[].class);

            // Process the recipes as needed
            // You can return them as JSON or perform other operations here

            return gson.toJson(recipes); // Return the recipes as JSON
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to read recipes.";
        }
    }
}
