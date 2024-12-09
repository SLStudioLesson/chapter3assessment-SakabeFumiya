package com.recipeapp.ui;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes(){
        try{
            ArrayList<Recipe> recipes = dataHandler.readData();
            if(recipes.isEmpty()){
                System.out.println("No recipes available.");
            } else{
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
                for(Recipe recipe : recipes){
                    System.out.println("Recipe Name:" + " " + recipe.getName());
                    System.out.println("Main Ingredients:");
                    for (Ingredient ingredient : recipe.getIngredients()) {
                        System.out.print(ingredient.getName() + ", ");
                    }
                    System.out.println();
                    System.out.println("-----------------------------------");
                }
            }
        } catch(IOException e){
            System.out.println("Error reading file:" + e.getMessage());
        }
    }

    private void addNewRecipe() {
        try {
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            String ingredient;
            while (true) {
                System.out.print("Enter ingredient (type 'done' when finished): ");
                ingredient = reader.readLine();
                if (ingredient.equals("done")) {
                    break;
                }
                ingredients.add(new Ingredient(ingredient));
            }

            Recipe recipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(recipe);

            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}