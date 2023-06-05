let counter = 1;
const addIngredient = document.querySelector("#ingredient-div");
const createRecipeForm = document.querySelector("#create-recipe-form");
//const createRecipe = document.querySelector("#create");
const customerId = "testcustomer";

function createNewIngredient() {
   counter++;
    // create the elements
    let fieldsetNew = document.createElement("fieldset");
    let legendNew = document.createElement("legend");
    let label1 = document.createElement("label");
    let input1 = document.createElement("input");
    let label2 = document.createElement("label");
    let input2 = document.createElement("input");
    let label3 = document.createElement("label");
    let input3 = document.createElement("input");
    let label4 = document.createElement("label");
    let input4 = document.createElement("input");
    let label5 = document.createElement("label");
    let input5 = document.createElement("input");
    let label6 = document.createElement("label");
    let input6 = document.createElement("input");
    let label7 = document.createElement("label");
    let input7 = document.createElement("input");
    let newLine = document.createElement("br");

    // Create the text nodes for labels
    let legendtext = document.createTextNode("Ingredient");
    let label1text = document.createTextNode("Amount:");
    let label2text = document.createTextNode("Measure Type:");
    let label3text = document.createTextNode("Food:");
    let label4text = document.createTextNode("Calories:");
    let label5text = document.createTextNode("Protein:");
    let label6text = document.createTextNode("Fat:");
    let label7text = document.createTextNode("Carbohydrates:");

    //set attributes for inputs
    fieldsetNew.setAttribute("id", "fieldset" + counter)

    input1.setAttribute("type", "text");
    input1.setAttribute("id", "amount" + counter);
    input1.setAttribute("required", "required");

    input2.setAttribute("type", "text");
    input2.setAttribute("id", "measure-type" + counter);
    input2.setAttribute("required", "required");
    
    input3.setAttribute("type", "text");
    input3.setAttribute("id", "food-name" + counter);
    input3.setAttribute("required", "required");

    input4.setAttribute("type", "text");
    input4.setAttribute("id", "calories" + counter);
    input4.setAttribute("required", "required");

    input5.setAttribute("type", "text");
    input5.setAttribute("id", "protein" + counter);
    input5.setAttribute("required", "required");

    input6.setAttribute("type", "text");
    input6.setAttribute("id", "fat" + counter);
    input6.setAttribute("required", "required");

    input7.setAttribute("type", "text");
    input7.setAttribute("id", "carbohydrates" + counter);
    input7.setAttribute("required", "required");

    //append text nodes and inputs to labels
    label1.appendChild(label1text);
    label1.appendChild(input1);
    label2.appendChild(label2text);
    label2.appendChild(input2);
    label3.appendChild(label3text);
    label3.appendChild(input3);
    label4.appendChild(label4text);
    label4.appendChild(input4);
    label5.appendChild(label5text);
    label5.appendChild(input5);
    label6.appendChild(label6text);
    label6.appendChild(input6);
    label7.appendChild(label7text);
    label7.appendChild(input7);
    legendNew.appendChild(legendtext);

    fieldsetNew.appendChild(label1);
    fieldsetNew.appendChild(label2);
    fieldsetNew.appendChild(label3);
    fieldsetNew.appendChild(newLine);
    fieldsetNew.appendChild(label4);
    fieldsetNew.appendChild(label5);
    fieldsetNew.appendChild(label6);
    fieldsetNew.appendChild(label7);
    fieldsetNew.appendChild(legendNew);
    
    //insert at end of form
    addIngredient.appendChild(fieldsetNew);
    
}    


createRecipeForm.onsubmit = async function(evt) {
evt.preventDefault();

const name = document.querySelector("#recipe-name").value;   
const servings = document.querySelector("#servings").value; 

    const newRecipe = {
    customerId: customerId,
    name: name,
    servings: servings,
    ingredients: [

     ]
    } ;
    
    for(var i = 1; i <= counter; i++){
  
      const amount = document.querySelector("#amount"+i).value;
      const measureType = document.querySelector("#measure-type"+i).value;
      const foodName = document.querySelector("#food-name"+i).value;
      const calories = document.querySelector("#calories"+i).value;
      const protein = document.querySelector("#protein"+i).value;
      const fat = document.querySelector("#fat"+i).value;
      const carbohydrates = document.querySelector("#carbohydrates"+i).value;
      const ingredientTemp = {
         amount: amount,
         measureType: measureType,
         foodName: foodName,
         macros: {
            calories: calories,
            protein: protein,
            fat: fat,
            carbohydrates: carbohydrates
         }
      }
      newRecipe.ingredients.push(ingredientTemp);
    }

var requestHeaders = {
   "Content-Type":"application/json",
   "Accept":"*/*",
   "Connection":"keep-alive",
   "Access-Control-Allow-Origin":"*"
}
                                                                              
axios.post('https://8mplwrsw2d.execute-api.us-west-2.amazonaws.com/prod/recipes', newRecipe, requestHeaders)
.then((response) => {
 console.log(response);
 window.location.href='index.html';
})    
}
