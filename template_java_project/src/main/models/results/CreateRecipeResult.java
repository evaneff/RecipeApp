package main.models.results;

import main.models.RecipeModel;

public class CreateRecipeResult {
    private RecipeModel recipe;

    public CreateRecipeResult(Builder builder) { this.recipe = builder.recipe; }

    public RecipeModel getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeModel recipe) { this.recipe = recipe; }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private RecipeModel recipe;

        public Builder withRecipe(RecipeModel recipe) {
            this.recipe = recipe;
            return this;
        }

        public CreateRecipeResult build() {return new CreateRecipeResult(this);}
    }



}

