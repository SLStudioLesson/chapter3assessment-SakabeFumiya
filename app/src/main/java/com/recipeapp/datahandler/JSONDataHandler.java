package com.recipeapp.datahandler;

import com.recipeapp.model.Recipe;
import java.io.*;
import java.util.ArrayList;

public class JSONDataHandler implements DataHandler{
    @Override
    public String getMode(){
        return "JSON";
    }

    @Override
    public ArrayList<Recipe> readData(){
        return null;
    }

    @Override
    public void writeData(Recipe recipe){
    }

    @Override
    public ArrayList<Recipe> searchData(){
        return null;
    }
}
