const customerId = "evamneff";
const myRecipes = document.querySelector("#recipes");

window.onload = async function getAllRecipes(evt) {
evt.preventDefault();
  
  var requestHeaders = {
    "Content-Type":"application/json",
    "Accept":"*/*",
    "Connection":"keep-alive",
    "Access-Control-Allow-Origin":"*"
  }
  
  axios.get('https://8mplwrsw2d.execute-api.us-west-2.amazonaws.com/prod/recipes/getallrecipes/' + customerId, requestHeaders)
    .then((response) => {
    console.log(response);
    populateMyRecipesList(response.data);
  })
}                           

function populateMyRecipesList(recipeData) {
   
  var recipes = recipeData.recipeList;

  recipes.forEach(recipeElement => {

    let idElement = recipeElement[0]; //id
    let nameElement = recipeElement[1]; //name

    let li = document.createElement("li");
    let aRecipe = document.createElement("a");
    let editButton = document.createElement("button");
    let aEdit = document.createElement("a");
    let deleteButton = document.createElement("button");
      
    let text = document.createTextNode(nameElement);
    let editButtonText = document.createTextNode("Edit Recipe");
    let deleteButtonText = document.createTextNode("Delete Recipe");
    
    aRecipe.setAttribute('href', `./GetRecipe.html?id=${idElement}`);
  
    editButton.setAttribute("id", "edit-" + idElement);
    editButton.setAttribute("type", "button");
    deleteButton.setAttribute("id", "delete-" + idElement);
    deleteButton.setAttribute("type", "button");
    
    editButton.appendChild(editButtonText);
    editButton.appendChild(aEdit);
    deleteButton.appendChild(deleteButtonText);

    aRecipe.appendChild(text);
    li.appendChild(aRecipe);
    li.appendChild(editButton);
    li.appendChild(deleteButton);
    myRecipes.appendChild(li); 
    
    
    document.querySelector("#edit-" + idElement).onclick = function() {
      location.href= `./UpdateRecipe.html?id=${idElement}`;
    }

    document.querySelector("#delete-" + idElement).onclick = function() {
      deleteRecipe(idElement);
    }  
    
  });
}  

function deleteRecipe(id) {

  let confirmed = confirm("Are you sure?")

  if (confirmed) {

    var requestHeaders = {
      "Content-Type":"application/json",
      "Accept":"*/*",
      "Connection":"keep-alive",
      "Access-Control-Allow-Origin":"*"
    }
  
    axios.delete('https://8mplwrsw2d.execute-api.us-west-2.amazonaws.com/prod/recipes/'+ id)
    .then((response) => {
      console.log(response);
      location.reload();
    })
  }
}
  function editRecipe() {
     location.href='updateRecipe.html';
  }