package main.models.results;

import main.models.RecipeModel;

public class GetRecipeResult {
    private RecipeModel recipe;

    public GetRecipeResult(Builder builder) { this.recipe = builder.recipe; }

    public RecipeModel getRecipe() { return recipe; }

    public void setRecipe(RecipeModel recipe) { this.recipe = recipe; }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private RecipeModel recipe;

        public Builder withRecipe(RecipeModel recipeToUse) {
            this.recipe = recipeToUse;
            return this;
        }

        public GetRecipeResult build() { return new GetRecipeResult(this); }
    }
}
