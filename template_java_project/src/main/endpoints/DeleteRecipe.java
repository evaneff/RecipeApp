package main.endpoints;

import com.amazonaws.Request;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.dynamodb.models.Recipe;
import main.dynamodb.models.RecipeDao;
import main.models.requests.DeleteRecipeRequest;
import main.models.results.DeleteRecipeResult;

import javax.inject.Inject;

public class DeleteRecipe implements RequestHandler<DeleteRecipeRequest, DeleteRecipeResult> {
    private final RecipeDao recipeDao;

    @Inject
    public DeleteRecipe() {
        recipeDao = new RecipeDao(new DynamoDBMapper((AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())).withRegion(Regions.US_WEST_2)).build()));
    }


    @Override
    public DeleteRecipeResult handleRequest(DeleteRecipeRequest deleteRecipeRequest, Context context) {
        String requestedId = deleteRecipeRequest.getId();
        Recipe recipe = new Recipe();
        recipe.setId(requestedId);

        recipeDao.deleteRecipe(recipe);

        String success = "success";
        return DeleteRecipeResult.builder()
                .withSuccess(success)
                .build();
    }
}
