package main.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Preconditions;
import main.dynamodb.models.RecipeDao;
import main.endpoints.*;

import javax.annotation.Generated;
import java.security.Provider;


public final class DaggerServiceComponent implements ServiceComponent {
    private Provider<DynamoDBMapper> providesDynamoDBMapperProvider;

    private DaggerServiceComponent(Builder builder) {
        //initialize(builder);
    }

    public static Builder builder() { return new Builder(); }

    public static ServiceComponent create() { return new Builder().build(); }

    private RecipeDao getRecipeDao() { return new RecipeDao(providesDynamoDBMapperProvider.get()); }

//    private void initialize(final Builder builder) {
//        this.providesDynamoDBMapperProvider =
//                DoubleCheck.provider(DaoModule_ProvidesDynamoDBMapperFactory.create(builder.daoModule));
//    }

    @Override
    public CreateRecipe provideCreateRecipe() { return new CreateRecipe(); }

    @Override
    public GetAllRecipes provideGetAllRecipes() { return new GetAllRecipes(); }

    @Override
    public GetRecipe provideGetRecipe() { return new GetRecipe(); }

    @Override
    public UpdateRecipe provideUpdateRecipe() { return new UpdateRecipe(); }

    public static final class Builder {
        private DaoModule daoModule;

        private Builder() {}

        public ServiceComponent build() {
            if (daoModule == null) {
                this.daoModule = new DaoModule();
            }
        return new DaggerServiceComponent(this);
        }

        public Builder daoModule(DaoModule daoModule) {
            this.daoModule = Preconditions.checkNotNull(daoModule);
            return this;
        }
    }
}
