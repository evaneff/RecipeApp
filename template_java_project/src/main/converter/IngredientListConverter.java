package main.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import main.dynamodb.models.Ingredient;

import java.util.List;

public class IngredientListConverter implements DynamoDBTypeConverter<String, List> {
    private static final Gson GSON = new Gson();


    @Override
    public String convert(List list) {
        return GSON.toJson(list);
    }

    @Override
    public List unconvert(String s) {
        return GSON.fromJson(s, new TypeToken<List<Ingredient>>() {} .getType());
    }
}

