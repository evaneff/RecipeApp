package main.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.dependency.DaggerServiceComponent;
import main.dependency.ServiceComponent;
import main.models.requests.UpdateRecipeRequest;
import main.models.results.UpdateRecipeResult;

public class UpdateRecipeProvider implements RequestHandler<UpdateRecipeRequest, UpdateRecipeResult> {
    @Override
    public UpdateRecipeResult handleRequest(UpdateRecipeRequest input, Context context) {
        return getApp().provideUpdateRecipe().handleRequest(input, context);
    }
    public ServiceComponent getApp() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
