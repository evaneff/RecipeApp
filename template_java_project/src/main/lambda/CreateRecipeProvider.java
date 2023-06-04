package main.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.dependency.DaggerServiceComponent;
import main.dependency.ServiceComponent;
import main.models.requests.CreateRecipeRequest;
import main.models.results.CreateRecipeResult;

public class CreateRecipeProvider implements RequestHandler<CreateRecipeRequest, CreateRecipeResult> {
    @Override
    public CreateRecipeResult handleRequest(CreateRecipeRequest input, Context context) {
        return getApp().provideCreateRecipe().handleRequest(input, context);
    }
    public ServiceComponent getApp() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
