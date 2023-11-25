# Recipe Finder API

The Recipe Finder API is a web service designed to provide functionalities related to managing and retrieving recipes. It typically includes endpoints that allow users to perform various operations such as:

Create, Retrieve, Update, and Delete Recipes: Endpoints allow users to perform CRUD (Create, Read, Update, Delete) operations on individual recipes.

The API can handle requests sent by clients (e.g., web applications, mobile apps) and provides responses with relevant recipe information in a structured format (typically JSON or XML). Users can interact with the API through HTTP methods like GET, POST, PUT, PATCH, and DELETE to perform the desired actions on recipes.

The main goal of the Recipe Finder API is to facilitate the management and retrieval of recipe-related information in an efficient and structured manner for applications or systems that require recipe-related functionalities.


### Prerequisites

Before you begin, make sure you have the following installed:

1. [JDK 17](https://learn.microsoft.com/en-gb/java/openjdk/download#openjdk-17) (or higher)

2. [Git](https://git-scm.com/downloads)

3. [Visual Studio Code](https://code.visualstudio.com/Download)
   1. [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
   2. [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)

Also make sure you have accounts for the following:

1. [GitHub](https://github.com/signup)

## Getting Started

To use the Recipe Finder API, follow the steps below:

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/recipe-finder-api.git
    cd recipe-finder-api


2. Install dependencies:
    ```sh
    ./mvnw clean dependency:resolve
    ```

    If you are on a Windows machine, that will be:
    ```cmd
    mvnw clean dependency:resolve
    ```    ```

3. Start the server:
    ```sh
    To start the API in VS Code, press `F5` or tap the 'Play' icon for the `api-assessment` app in the Spring Boot Dashboard.

    Alternatively, to start the API from the terminal, run the following command:

    ```sh
    ./mvnw spring-boot:run
    ```

    Or on Windows:

    ```cmd
    mvnw spring-boot:run
    ```

## API Endpoints

### Search all recipes

- **Endpoint**: `/recipes`
- **Method**: `GET`
- **Description**: Retrieves all available recipes.

### Search recipe by name

- **Endpoint**: `/recipes/search`
- **Method**: `GET`
- **Description**: Searches for a recipe by its name.

### Search recipes by ingredients

- **Endpoint**: `/recipes/findByIngredients`
- **Method**: `GET`
- **Description**: Finds recipes based on specified ingredients.

### Search recipes by meal type

- **Endpoint**: `/recipes/findByCategory`
- **Method**: `GET`
- **Description**: Finds recipes based on meal category eg Breakfast, Lunch, Dinner.

### Search recipes by cooking method

- **Endpoint**: `/recipes/findByCookingMethod`
- **Method**: `GET`
- **Description**: Finds recipes based on cooking methods eg Microwave, Kettle, Stove-top and Oven.

### Search recipes by cultural influence

- **Endpoint**: `/recipes/findByCulturalInfluence`
- **Method**: `GET`
- **Description**: Finds recipes based on cultural influence eg African cuisine, Asian cuisine.

### Create recipe

- **Endpoint**: `/recipes`
- **Method**: `POST`
- **Description**: Create new recipe.

### Update recipe

- **Endpoint**: `/recipes/{recipeID}`
- **Method**: `PUT`
- **Description**: Update entire recipe by ID number.

### Update recipe

- **Endpoint**: `/recipes/{recipeID}`
- **Method**: `PATCH`
- **Description**: Update parts of recipe by ID number.

### Delete recipe

- **Endpoint**: `/recipes/{recipeID}`
- **Method**: `DELETE`
- **Description**: Delete entire recipe by ID number.



## Usage Examples

### Search for recipes

- **Example**: Search all recipes
    ```http://localhost:8080/
    GET /recipes
    ```

- **Example**: Search for a recipe by name
    ```http://localhost:8080/
    GET /recipes/search?name=Tuna%20Curry%20with%20Rice
    ```

     **Example**: Search for a recipe by ingredients
    ```http://localhost:8080/
    GET /recipes/findByIngredients?ingredient=Rice&ingredient=Tuna
    ```

     **Example**: Search for a recipe by meal type
    ```http://localhost:8080/
    GET /recipes/findByCategory?mealType=Dinner
    ```

     **Example**: Search for a recipe by cooking method
    ```http://localhost:8080/
    GET /recipes/findByCookingMethod?method=Microwave
    ```

     **Example**: Search for a recipe by cultural influence
    ```http://localhost:8080/
    GET /recipes/findByCulturalInfluence?culturalInfluence=African%20Cuisine
    ```

### Add a new recipe

- **Example**: Create a new recipe
    ```http://localhost:8080/
    POST /recipes
    Content-Type: application/json

    {
        "id": "031",
        "name": "Pease Pudding Dhall",
        "mealType": "Dinner",
        "cookingMethod": "Stove Top",
        "ingredients": ["Canned Pease Pudding", "Onions", "Turmeric", "Cumin",      "Mustard Seeds", "Chilli"],
        "instructions": "Fry onions, garlic and chillies with cumin and mustard seeds. Add a chopped fresh tomato. Add canned pease pudding, continue cooking by adding water and turmeric",
        "culturalInfluence": "None",
        "type": "regular"
    }
    ```


### Update recipe

- **Example**: Update entire recipe
    ```http://localhost:8080/
    PUT /recipes/031
    Content-Type: application/json

    {
        "id": "031",
        "name": "Tinned Vegetable Stew",
        "mealType": "Dinner",
        "cookingMethod": "Stovetop",
        "ingredients": ["Canned tomatoes", "Canned mixed vegetables", "Canned beans", "Canned corn", "Vegetable broth"],
        "instructions": "1. Heat vegetable broth in a pot.\n2. Add canned tomatoes, mixed vegetables, beans, and corn.\n3. Simmer for 20 minutes.\n4. Serve hot." "culturalInfluence": "None",
        "type": "regular"
    }
    ```

- **Example**: Update part of recipe
    ```http://localhost:8080/
    PATCH /recipes/031
    Content-Type: application/json

    {
        "name": "Jelly",
        "mealType": "Dessert",
        "cookingMethod": "Microwave"
    }
    ```

### Delete recipe

 **Example**: Delete recipe
    ```http://localhost:8080/
    DELETE /recipes/031
    ```



## Contributing

Contributions are welcome! To contribute to the Recipe Finder API, follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Create a new Pull Request.

## License

This project is licensed under the [MIT License](LICENSE).
