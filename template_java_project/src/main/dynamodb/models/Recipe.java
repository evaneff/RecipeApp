package main.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import main.converter.IngredientListConverter;

import java.util.List;


/**
 * Represents a record in the recipes table.
 */
@DynamoDBTable(tableName = "recipes")
public class Recipe {

    private String id;
    private String customerId;
    private String name;
    private int servings;
    private List<Ingredient> ingredients;
    private Macronutrient macrosPerServing;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "customer_id")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "servings")
    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    @DynamoDBTypeConverted(converter = IngredientListConverter.class)
    @DynamoDBAttribute(attributeName = "ingredients")
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @DynamoDBAttribute(attributeName = "macros_per_serving")
    public Macronutrient getMacrosPerServing() {
        return macrosPerServing;
    }

    public void setMacrosPerServing(Macronutrient macrosPerServing) {
        this.macrosPerServing = macrosPerServing;
    }
}
