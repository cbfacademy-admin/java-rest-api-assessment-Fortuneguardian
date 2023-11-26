package com.cbfacademy.apiassessment;

import java.util.List;

public class SpecialRecipe extends Recipe {

    public SpecialRecipe(int id, String name, String mealType, String cookingMethod, List<String> ingredients,
            String instructions, String culturalInfluence) {

        super(id, name, mealType, cookingMethod, ingredients, instructions, culturalInfluence, culturalInfluence);
    }

}
