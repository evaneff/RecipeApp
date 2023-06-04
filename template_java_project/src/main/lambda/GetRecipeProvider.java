package main.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.dependency.DaggerServiceComponent;
import main.dependency.ServiceComponent;
import main.models.requests.GetRecipeRequest;
import main.models.results.GetRecipeResult;

public class GetRecipeProvider implements RequestHandler<GetRecipeRequest, GetRecipeResult> {
    @Override
    public GetRecipeResult handleRequest(GetRecipeRequest input, Context context) {
        return getApp().provideGetRecipe().handleRequest(input, context);
    }
    public ServiceComponent getApp() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
