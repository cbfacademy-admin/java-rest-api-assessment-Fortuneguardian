package com.cbfacademy.apiassessment;

import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private String mealType;
    private String cookingMethod;
    private List<String> ingredients;
    private String instructions;

    public Recipe(int id, String name, String mealType, String cookingMethod, List<String> ingredients, String instructions) {
        this.id = id;
        this.name = name;
        this.mealType = mealType;
        this.cookingMethod = cookingMethod;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
    
    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getMealType() {
        return mealType;
    }
    public String getCookingMethod() {
        return cookingMethod;
    }
    public List<String> getIngredients() {
        return ingredients;
    }
    public String getInstructions() {
        return instructions;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

   
}


