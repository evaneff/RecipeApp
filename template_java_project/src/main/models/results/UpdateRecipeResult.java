package main.models.results;

import main.models.RecipeModel;

public class UpdateRecipeResult {
    private RecipeModel recipe;

    public UpdateRecipeResult(Builder builder) { this.recipe = builder.recipe; }

    public RecipeModel getRecipe() { return recipe; }

    public void setRecipe(RecipeModel recipe) { this.recipe = recipe; }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private RecipeModel recipe;

        public Builder withRecipe(RecipeModel recipeToUse) {
            this.recipe = recipeToUse;
            return this;
        }

        public UpdateRecipeResult build() { return new UpdateRecipeResult(this); }
    }

}
