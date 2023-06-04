package main.models.results;

import main.dynamodb.models.Recipe;

import java.util.List;

public class GetAllRecipesResult {
    private List<List<String>> recipeList;

    public GetAllRecipesResult(Builder builder) { this.recipeList = builder.recipeList; }

    public List<List<String>> getRecipeList() { return recipeList; }

    public void setRecipeList(List<List<String>> recipeList) { this.recipeList = recipeList; }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private List<List<String>> recipeList;

        public Builder withRecipeList(List<List<String>> recipeListToUse) {
            this.recipeList = recipeListToUse;
            return this;
        }

        public GetAllRecipesResult build() { return new GetAllRecipesResult(this); }
    }
}
