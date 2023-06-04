package main.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Ingredient {

    private double amount;
    private String measureType;
    private String foodName;
    private Macronutrient macros;


    public Ingredient() {}

    public Ingredient(double amount, String measureType, String foodName, Macronutrient macros) {
        this.amount = amount;
        this.measureType = measureType;
        this.foodName = foodName;
        this.macros = macros;
    }
    @DynamoDBAttribute(attributeName = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @DynamoDBAttribute(attributeName = "measureType")
    public String getMeasureType() {
        return measureType;
    }

    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }

    @DynamoDBAttribute(attributeName = "foodName")
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @DynamoDBAttribute(attributeName = "macros")
    public Macronutrient getMacros() { return macros; }

    public void setMacros(Macronutrient macros) {
        this.macros = macros;
    }
}
