package main.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import main.dependency.DaggerServiceComponent;
import main.dependency.ServiceComponent;
import main.models.requests.DeleteRecipeRequest;
import main.models.results.DeleteRecipeResult;

public class DeleteRecipeProvider implements RequestHandler<DeleteRecipeRequest, DeleteRecipeResult> {

    @Override
    public DeleteRecipeResult handleRequest(DeleteRecipeRequest input, Context context) {
        return getApp().provideDeleteRecipe().handleRequest(input, context);
    }

    public ServiceComponent getApp() {
    ServiceComponent dagger = DaggerServiceComponent.create();
    return dagger;
    }
}
