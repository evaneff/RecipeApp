package main.endpoints;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.dynamodb.models.RecipeDao;
import main.models.requests.GetAllRecipesRequest;
import main.models.results.GetAllRecipesResult;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GetAllRecipes implements RequestHandler<GetAllRecipesRequest, GetAllRecipesResult> {
    private final RecipeDao recipeDao;

    @Inject
    public GetAllRecipes(DynamoDBMapper mapper) {
        recipeDao = new RecipeDao(mapper);
    }

    public GetAllRecipes() {
        recipeDao = new RecipeDao((AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())).withRegion(Regions.US_WEST_2)).build());
    }

    @Override
    public GetAllRecipesResult handleRequest(GetAllRecipesRequest getAllRecipesRequest, com.amazonaws.services.lambda.runtime.Context context) {

        List<List<String>> myRecipes = new ArrayList<>();

        ScanResult result = recipeDao.getAllRecipes(getAllRecipesRequest.getCustomerId());
        for (Map<String, AttributeValue> item : result.getItems()) {
            List<String> recipe = new ArrayList<>();
            recipe.add(item.get("id").getS());
            recipe.add(item.get("name").getS());
            myRecipes.add(recipe);
        }
        return GetAllRecipesResult.builder()
                .withRecipeList(myRecipes)
                .build();
    }
}
