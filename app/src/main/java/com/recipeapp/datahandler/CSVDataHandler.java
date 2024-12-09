package com.recipeapp.datahandler;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;
import java.io.*;
import java.util.ArrayList;

public class CSVDataHandler implements DataHandler{
    private String filePath;

    public CSVDataHandler(){
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath){
        this.filePath = filePath;
    }

    @Override
    public String getMode(){
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException{
        ArrayList<Recipe> recipes = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                String[]parts = line.split(",");
                String recipeName = parts[0];
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    ingredients.add(new Ingredient(parts[i].trim()));
                }
                recipes.add(new Recipe(recipeName, ingredients));
            }
        }
        return recipes;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(recipe.getName());
            for(Ingredient ingredient : recipe.getIngredients()){
                writer.write("," + ingredient.getName());
            }
            writer.newLine();
        }catch(IOException e){
            throw new IOException("Error while writing to file: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword){
        return null;
    }
}
