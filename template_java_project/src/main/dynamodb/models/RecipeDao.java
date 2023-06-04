package main.dynamodb.models;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import main.exceptions.RecipeNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeDao {
    private  DynamoDBMapper dynamoDBMapper = null;
    private  AmazonDynamoDB client = null;

    public RecipeDao(DynamoDBMapper dynamoDBMapper) { this.dynamoDBMapper = dynamoDBMapper; }

    public RecipeDao(AmazonDynamoDB client) { this.client = client; }

    public Recipe getRecipe(String id) {
        Recipe recipe = this.dynamoDBMapper.load(Recipe.class, id);

        if (recipe == null) {
            throw new RecipeNotFoundException("Could not find recipe with id " + id);
        }
        return recipe;
    }
    public Recipe saveRecipe(Recipe recipe) {
        dynamoDBMapper.save(recipe);
        return recipe;
    }

    public void deleteRecipe(Recipe recipe) {
        dynamoDBMapper.delete(recipe);
    }

    public ScanResult getAllRecipes(String customerId) {
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":customer_id", new AttributeValue().withS(customerId));
        ScanRequest scanRequest = new ScanRequest()
                .withTableName("recipes")
                .withFilterExpression("customer_id = :customer_id")
                .withExpressionAttributeValues(expressionAttributeValues);

        ScanResult result = client.scan(scanRequest);

        return result;
    }
}


