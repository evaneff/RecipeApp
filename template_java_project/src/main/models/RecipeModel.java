package main.models;

import com.amazonaws.services.dynamodbv2.xspec.B;
import main.dynamodb.models.Ingredient;
import main.dynamodb.models.Macronutrient;

import java.util.List;
import java.util.Objects;

public class RecipeModel {

    private String id;
    private String customerId;
    private String name;
    private int servings;
    private List<Ingredient> ingredients;
    private MacronutrientModel macrosPerServing;

    public RecipeModel() {}

    public RecipeModel(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.name = builder.name;
        this.servings = builder.servings;
        this.ingredients = builder.ingredients;
        this.macrosPerServing = builder.macrosPerServing;
    }
    public String getId() { return id; }

    public void setId() { this.id = id; }

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

    public MacronutrientModel getMacrosPerServing() {
        return macrosPerServing;
    }

    public void setMacrosPerServing(MacronutrientModel macrosPerServing) {
        this.macrosPerServing = macrosPerServing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeModel)) return false;
        RecipeModel that = (RecipeModel) o;
        return getServings() == that.getServings() && Objects.equals(getId(), that.getId()) && Objects.equals(getCustomerId(), that.getCustomerId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getIngredients(), that.getIngredients()) && Objects.equals(getMacrosPerServing(), that.getMacrosPerServing());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomerId(), getName(), getServings(), getIngredients(), getMacrosPerServing());
    }

    @Override
    public String toString() {
        return "RecipeModel{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", servings=" + servings +
                ", ingredients=" + ingredients +
                ", macrosPerServing=" + macrosPerServing +
                '}';
    }

    public static Builder builder() { return new Builder(); }
    public static final class Builder {
        private String id;
        private String customerId;
        private String name;
        private int servings;
        private List<Ingredient> ingredients;
        private MacronutrientModel macrosPerServing;

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
        public Builder withMacrosPerServing(MacronutrientModel macrosPerServingToUse) {
            this.macrosPerServing = macrosPerServingToUse;
            return this;
        }
        public RecipeModel build() { return new RecipeModel(this); }
    }

}
