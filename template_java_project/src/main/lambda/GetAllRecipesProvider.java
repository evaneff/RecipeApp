package main.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.dependency.DaggerServiceComponent;
import main.dependency.ServiceComponent;
import main.models.requests.GetAllRecipesRequest;
import main.models.results.GetAllRecipesResult;

public class GetAllRecipesProvider implements RequestHandler<GetAllRecipesRequest, GetAllRecipesResult> {
    @Override
    public GetAllRecipesResult handleRequest(GetAllRecipesRequest input, Context context) {
        return getApp().provideGetAllRecipes().handleRequest(input, context);
    }
    public ServiceComponent getApp() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
