const recipediv = document.querySelector("#recipe");
const editRecipe = document.querySelector("#update")

const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');

window.onload = async function(evt) {
    evt.preventDefault();
    
    axios.get('https://8mplwrsw2d.execute-api.us-west-2.amazonaws.com/prod/recipes/' + id)
    .then((response) => {
        console.log(response);
        populateRecipe(response.data);
    })
}

function populateRecipe(recipeData) {

    var recipeItem = recipeData.recipe;
    var ingredientsItem = recipeData.recipe.ingredients;
    var macrosPerServingItem = recipeData.recipe.macrosPerServing;

    const recipeName = document.createElement("h2");
    const recipeServings = document.createElement("p");
    const recipeIngredients = document.createElement("ul");
    const recipeMacrosPerServing = document.createElement("ul");

    const nameText = document.createTextNode(recipeItem.name);
    const servingsText = document.createTextNode("Servings: " + recipeItem.servings);

    for (var i = 0; i < ingredientsItem.length; i++) {
    
        const item = document.createElement("li");
        const itemText = document.createTextNode(ingredientsItem[i].amount + " " + ingredientsItem[i].measureType + " " + ingredientsItem[i].foodName);
        item.appendChild(itemText);
        recipeIngredients.appendChild(item);
    }
   
    
    const caloriesItem = document.createElement("li");
    const proteinItem = document.createElement("li");
    const fatItem = document.createElement("li");
    const carbohydratesItem = document.createElement("li");
    const macrosHeader = document.createElement("h3");

    const caloriesText = document.createTextNode("Calories: " + macrosPerServingItem.calories);
    const proteinText = document.createTextNode("Protein: " + macrosPerServingItem.protein);
    const fatText = document.createTextNode("Fat: " + macrosPerServingItem.fat);
    const carbohydratesText = document.createTextNode("Carbohydrates: " + macrosPerServingItem.carbohydrates);
    const headerText = document.createTextNode("Macros Per Serving: ");

    recipeName.appendChild(nameText);
    recipeServings.appendChild(servingsText);

    caloriesItem.appendChild(caloriesText);
    proteinItem.appendChild(proteinText);
    fatItem.appendChild(fatText);
    carbohydratesItem.appendChild(carbohydratesText);
    macrosHeader.appendChild(headerText);

    recipeMacrosPerServing.appendChild(caloriesItem);
    recipeMacrosPerServing.appendChild(proteinItem);
    recipeMacrosPerServing.appendChild(fatItem);
    recipeMacrosPerServing.appendChild(carbohydratesItem);

    recipediv.appendChild(recipeName);
    recipediv.appendChild(recipeServings);
    recipediv.appendChild(recipeIngredients);
    recipediv.appendChild(macrosHeader);

    recipediv.appendChild(recipeMacrosPerServing);
}




