package main.endpoints;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.converter.ModelConverter;
import main.dynamodb.models.Recipe;
import main.dynamodb.models.RecipeDao;
import main.models.RecipeModel;
import main.models.requests.GetRecipeRequest;
import main.models.results.GetRecipeResult;
import javax.inject.Inject;


public class GetRecipe implements RequestHandler<GetRecipeRequest, GetRecipeResult> {
    private final RecipeDao recipeDao;

    @Inject
    public GetRecipe() {
        recipeDao = new RecipeDao(new DynamoDBMapper((AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())).withRegion(Regions.US_WEST_2)).build()));
    }
    @Override
    public GetRecipeResult handleRequest(GetRecipeRequest getRecipeRequest, com.amazonaws.services.lambda.runtime.Context context) {
        String requestedId = getRecipeRequest.getId();
        Recipe recipe = recipeDao.getRecipe(requestedId);


        RecipeModel recipeModel = new ModelConverter().toRecipeModel(recipe);

        return GetRecipeResult.builder()
                .withRecipe(recipeModel)
                .build();
    }

}
