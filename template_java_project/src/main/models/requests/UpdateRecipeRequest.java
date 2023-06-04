package main.models.requests;

import main.dynamodb.models.Ingredient;

import java.util.List;
import java.util.Objects;

public class UpdateRecipeRequest {

    private String id;
    private String customerId;
    private String name;
    private int servings;
    private List<Ingredient> ingredients;

    public UpdateRecipeRequest() {}



    public UpdateRecipeRequest(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.name = builder.name;
        this.servings = builder.servings;
        this.ingredients = builder.ingredients;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateRecipeRequest)) return false;
        UpdateRecipeRequest that = (UpdateRecipeRequest) o;
        return getServings() == that.getServings() && Objects.equals(getId(), that.getId()) && Objects.equals(getCustomerId(), that.getCustomerId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getIngredients(), that.getIngredients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomerId(), getName(), getServings(), getIngredients());
    }

    @Override
    public String toString() {
        return "UpdateRecipeRequest{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", servings=" + servings +
                ", ingredients=" + ingredients +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String id;
        private String customerId;
        private String name;
        private int servings;
        private List<Ingredient> ingredients;

        public Builder() {}

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public Builder withCustomerId(String customerIdToUse) {
            this.customerId = customerIdToUse;
            return this;
        }

        public Builder withName(String nameToUse) {
            this.name = nameToUse;
            return this;
        }
        public Builder withServings(int servingsToUse) {
            this.servings = servingsToUse;
            return this;
        }
        public Builder withIngredients(List<Ingredient> ingredientsToUse) {
            this.ingredients = ingredientsToUse;
            return this;
        }
        public UpdateRecipeRequest build() { return new UpdateRecipeRequest(this); }
    }
}
