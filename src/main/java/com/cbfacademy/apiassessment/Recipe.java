package com.cbfacademy.apiassessment;

import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private String mealType;
    private String cookingMethod;
    private List<String> ingredients;
    private String instructions;
    private String culturalInfluence;
    private String type;

    public Recipe(int id, String name, String mealType, String cookingMethod, List<String> ingredients,
            String instructions, String culturalInfluence, String type) {
        this.id = id;
        this.name = name;
        this.mealType = mealType;
        this.cookingMethod = cookingMethod;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.culturalInfluence = culturalInfluence;
        this.type = type;
    }

    // Getters
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

    public String getCulturalInfluence() {
        return culturalInfluence;
    }

    public String getType() {
        return type;
    }

    // Setters
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

    public void setCulturalInfluence(String culturalInfluence) {
        this.culturalInfluence = culturalInfluence;
    }
    public void setType(String type) {
        this.type = type;
    }

}
