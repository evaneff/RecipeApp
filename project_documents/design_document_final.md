# Eva Neff Design Document


## Recipe Macro Finder Design

## 1. Problem Statement

There are many websites that allow searching of databases to find the macronutrient content of foods.
Recipe Macro Finder will allow users to create custom recipes and view the total calories and macronutrients of all
the ingredients by serving size.

## 2. Top Questions to Resolve in Review

1. Should I use request and result classes?
2. Should I use model and converter classes?   
3. Should GetMacrosPerServing be connected to/ included in GetRecipe?

## 3. Use Cases

U1. As a customer, I want to create custom recipes.

U2. As a customer, I want to view all of my saved recipes.

U3. As a customer, I want to update the ingredients in existing recipes.
    
U4. As a customer, I want to update the number of servings in a recipe.

U5. As a customer, I want to view the total calories and macronutrient per serving of a recipe.

U6. As a customer, I want to input custom foods into database.

## 4. Project Scope

### 4.1. In Scope

- Creating, retrieving, and updating a recipe.
- Calculate calories and macronutrients for each ingredient.
- Retrieving the recipes calorie and macronutrient totals per serving.

### 4.2. Out of Scope

- Sharing totals with another user.
- Tracking total calorie and macronutrient consumption. 


# 5. Proposed Architecture Overview

This project will provide the minimum lovable product including creating, retrieving, 
and updating a recipe and also creating a user's own database of foods.
We will use API Gateway and Lambda to create seven endpoints (`CreateRecipe`, `GetRecipe`, `UpdateRecipe`,
`GetMacrosPerServing`, `GetAllRecipes`, `CreateFood` and `GetFood`) that will handle the creation, update, 
and retrieval of recipes and foods to satisfy our requirements.

We will store recipes and foods in separate tables in DynamoDB to allow users access to their own custom 
recipes and foods.

Recipe Macro Finder will also provide a web interface for users to manage their recipes and foods. A template_java_project.src.main page providing 
a list view of all of their recipes will let them create new recipes and link off to pages per-recipe to update them.

[Link to project class diagram](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/RecipeProject.puml)

# 6. API

## 6.1. Public Models
```
// Recipe

String customerId;
String name;
int servings;
List<Double> amounts;
List<MeasureType> measureTypes; 
List<Food> ingredients;
```
```
// Food

String food;
String brand;
double amount;
String measureType;
Macronutrient macros;
```
```
// Macronutrient

int calories;
int protein;
int fat;
int carbohydrates;

```
## 6.2. *Create Recipe Endpoint*

- Accepts `POST` requests to `/Recipe`
- Accepts data to create a new recipe with provided customerId and name. 
- There is a utility class that validates the recipe name does not contain invalid characters.
- If the recipe name contains invalid characters, will throw an `InvalidAttributeValueException`.
- Creates a recipe object with empty servings, amounts, measureType, and ingredients, so we can add them through UpdateRecipe.
- Saves recipe to database.
- Converts recipe to a RecipeModel, returns result.
- [Link to Create Recipe sequence diagram](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/CreateRecipe.puml)

## 6.3 *Get Recipe Endpoint*

- Accepts `GET` requests to `/Recipe /requestedName`
- Accepts a recipe name to return the corresponding recipe.
- If the given recipe name is not found, will throw a `RecipeNotFoundException`
- Populates a Recipe object
- Converts recipe to a RecipeModel, returns result.
- [Link to Get Recipe sequence diagram](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/GetRecipe.puml)

## 6.4 *Update Recipe Endpoint*

- Accepts `PUT` requests to `/Recipe /name`
- Accepts data to update a recipe.
- There is a utility class that validates the requested recipe name does not contain invalid characters.
- If the recipe name contains invalid characters, will throw an `InvalidAttributeValueException`.
- Populates a Recipe object.
- Updates the requested attributes.
- Saves changes to database.
- Converts recipe to RecipeModel, returns result.
- [Link to Update Recipe sequence diagram](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/UpdateRecipe.puml)

## 6.5 *Get Macros Per Serving Endpoint*

- Accepts `GET` requests to `Recepe /name`
- Accepts a recipe name to return the macros per serving
- If the given recipe name is not found, will throw a `RecipeNotFoundException`
- Populates a recipe object
- Loops through Macronutrients to total and divide by servings
- Converts Macronutrients to MacronutrientModel, returns result.
- [Link to Macros Per Serving sequence diagram](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/GetMacrosPerServing.puml)

## 6.6 *Get All Recipes Endpoint*

- Accepts `GET` requests to `/allRecipes /customerId`
- Accepts a customerId to return a list of recipe names.
- Populates a list of Recipes, returns result.
- [Link to Get All Recipes sequence diagram](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/GetAllRecipes.puml)

## 6.7 *Create Food Endpoint*

- Accepts `POST` requests to `/Food`
- Accepts data to create a new food with provided food, brand, amount, measureType, and macros. 
- There is a utility class that validates the food does not contain invalid characters.
- If the food contains invalid characters, will throw an `InvalidAttributeValueException`.
- Creates a food object with the given attributes.
- Saves food to database.
- Converts food to a FoodModel, returns result.
- [Link to Create Food sequence diagram](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/CreateFood.puml)

## 6.8 *Get Food Endpoint*

- Accepts `GET` requests to `/Food /requestedName`
- Accepts a food name to return the corresponding food.
- If the given food name is not found, will throw a `FoodNotFoundException`
- Populates a Food object
- Converts food to a FoodModel, returns result.
- [Link to Get Food sequence diagram](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/GetFood.puml)

## 6.9 *Get All Food Endpoint*

- Accepts `GET` requests to `/Food /customerId`
- Accepts a customerId to return a list of foods.
- Populates a list of Foods, returns result.
- [Link to Get All Foods sequence diagram](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/GetAllFoods.puml)

# 7. Tables

## 7.1 `recipes`
```
customerId // partition key, String
name // sort key, String
servings // int
amounts // list
measureType // list
ingredients // list
```
## 7.2 `foods`
```
Food
customerId // partition key, String
food // sort key, String
brand // String
amount // double
measureType // MeasureType
macros // Macronutrient
```
# 8. Pages

## Get All Recipes / Create Recipe
![GetAllRecipes - CreateRecipe](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/allrecipes-createrecipe.png)
## Create Food / Get Recipe
![CreateFood - GetRecipe](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/createfood-getrecipe.png)
## Get All Foods / Update Recipe
![GeteAllFoods - UpdateRecipe](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/allfoods-getfood.png)
## Update Recipe
![Update Recipe](/Users/evamneff/BloomTech/Unit 5/Module 1/bd-team-project-evaneff/project_documents/updaterecipe.png)
