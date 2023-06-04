package main.converter;

import main.dynamodb.models.Macronutrient;
import main.dynamodb.models.Recipe;
import main.models.MacronutrientModel;
import main.models.RecipeModel;

public class ModelConverter {

    public RecipeModel toRecipeModel(Recipe recipe) {
        return RecipeModel.builder()
                .withId(recipe.getId())
                .withCustomerId(recipe.getCustomerId())
                .withName(recipe.getName())
                .withServings(recipe.getServings())
                .withIngredients(recipe.getIngredients())
                .withMacrosPerServing(toMacronutrientModel(recipe.getMacrosPerServing()))
                .build();
    }
    public MacronutrientModel toMacronutrientModel(Macronutrient macronutrient) {
        return MacronutrientModel.builder()
                .withCalories(macronutrient.getCalories())
                .withProtein(macronutrient.getProtein())
                .withFat(macronutrient.getFat())
                .withCarbohydrates(macronutrient.getCarbohydrates())
                .build();

    }
}
