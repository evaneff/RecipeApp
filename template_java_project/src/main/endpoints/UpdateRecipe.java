package main.endpoints;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.converter.ModelConverter;
import main.dynamodb.models.Recipe;
import main.dynamodb.models.RecipeDao;
import main.models.RecipeModel;
import main.models.requests.UpdateRecipeRequest;
import main.models.results.UpdateRecipeResult;
import javax.inject.Inject;


public class UpdateRecipe implements RequestHandler<UpdateRecipeRequest, UpdateRecipeResult> {
    private final RecipeDao recipeDao;

    @Inject
    public UpdateRecipe(DynamoDBMapper mapper) {
        recipeDao = new RecipeDao(mapper);
    }

    public UpdateRecipe() {
        recipeDao = new RecipeDao(new DynamoDBMapper((AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())).withRegion(Regions.US_WEST_2)).build()));
    }
    @Override
    public UpdateRecipeResult handleRequest(final UpdateRecipeRequest updateRecipeRequest, Context context) {
        String requestedId = updateRecipeRequest.getId();
        Recipe recipe = recipeDao.getRecipe(requestedId);
        recipe.setId(updateRecipeRequest.getId());
        recipe.setCustomerId(updateRecipeRequest.getCustomerId());
        recipe.setName(updateRecipeRequest.getName());
        recipe.setServings(updateRecipeRequest.getServings());
        recipe.setIngredients(updateRecipeRequest.getIngredients());
        recipeDao.saveRecipe(recipe);
        RecipeModel recipeModel = new ModelConverter().toRecipeModel(recipe);

        return UpdateRecipeResult.builder()
                .withRecipe(recipeModel)
                .build();
    }
}
