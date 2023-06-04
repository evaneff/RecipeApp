package main.endpoints;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.converter.ModelConverter;
import main.dynamodb.models.Ingredient;
import main.dynamodb.models.Macronutrient;
import main.dynamodb.models.Recipe;
import main.dynamodb.models.RecipeDao;
import main.models.RecipeModel;
import main.models.requests.CreateRecipeRequest;
import main.models.results.CreateRecipeResult;
import main.util.RecipeServiceUtils;
import javax.inject.Inject;
import javax.naming.directory.InvalidAttributeValueException;
import java.util.List;


public class CreateRecipe implements RequestHandler<CreateRecipeRequest, CreateRecipeResult> {
    private final RecipeDao recipeDao;

    @Inject
    public CreateRecipe() {
        recipeDao = new RecipeDao(new DynamoDBMapper((AmazonDynamoDB) ((AmazonDynamoDBClientBuilder) ((AmazonDynamoDBClientBuilder) AmazonDynamoDBClientBuilder.standard().withCredentials(DefaultAWSCredentialsProviderChain.getInstance())).withRegion(Regions.US_WEST_2)).build()));
    }
    @Override
    public CreateRecipeResult handleRequest(final CreateRecipeRequest createRecipeRequest, Context context) {

        try {
        boolean name = RecipeServiceUtils.isValidString(createRecipeRequest.getName());
        } catch (InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
        Recipe recipe = new Recipe();
        recipe.setId(RecipeServiceUtils.generateRecipeId());
        recipe.setCustomerId(createRecipeRequest.getCustomerId());
        recipe.setName(createRecipeRequest.getName());
        recipe.setServings(createRecipeRequest.getServings());
        recipe.setIngredients(createRecipeRequest.getIngredients());

        //get macros
        List<Ingredient> ingredients = recipe.getIngredients();
        int servings = recipe.getServings();
        Macronutrient macros = new Macronutrient();

        for (Ingredient ingredient : ingredients) {
            macros.setCalories(macros.getCalories() + ingredient.getMacros().getCalories());
            macros.setProtein(macros.getProtein() + ingredient.getMacros().getProtein());
            macros.setFat(macros.getFat() + ingredient.getMacros().getFat());
            macros.setCarbohydrates(macros.getCarbohydrates() + ingredient.getMacros().getCarbohydrates());
        }
        //divide by servings
        Macronutrient macrosPerServing = new Macronutrient();
        macrosPerServing.setCalories(macros.getCalories() / servings);
        macrosPerServing.setProtein(macros.getProtein() / servings);
        macrosPerServing.setFat(macros.getFat() / servings);
        macrosPerServing.setCarbohydrates(macros.getCarbohydrates() / servings);

        recipe.setMacrosPerServing(macrosPerServing);

        //save recipe
        recipeDao.saveRecipe(recipe);
        //to RecipeModel
        RecipeModel recipeModel = new ModelConverter().toRecipeModel(recipe);
        return CreateRecipeResult.builder()
                .withRecipe(recipeModel)
                .build();
    }

}
