package main.dependency;

import dagger.Component;
import main.endpoints.*;

import javax.inject.Singleton;

@Singleton
@Component(modules = DaoModule.class)

public interface ServiceComponent {

    CreateRecipe provideCreateRecipe();
    GetAllRecipes provideGetAllRecipes();
    GetRecipe provideGetRecipe();
    UpdateRecipe provideUpdateRecipe();
    DeleteRecipe provideDeleteRecipe();

}
