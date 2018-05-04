package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static Sandwich parseSandwichJson(String json)
            throws JSONException {
        String Name = "name";
        String Main_Name = "mainName";
        String Also_Known_As = "alsoKnownAs";
        String Place_Of_Origin = "placeOfOrigin";
        String Description = "description";
        String Image = "image";
        String Ingredients = "ingredients";


        Sandwich sandwich = null;
        JSONObject SandInfo = new JSONObject(json);
        JSONObject SandName = SandInfo.getJSONObject(Name);


        String SandMainName = SandName.getString(Main_Name);
        String SandOrigin = SandInfo.getString(Place_Of_Origin);
        String SandDescription = SandInfo.getString(Description);
        String SandImage = SandInfo.getString(Image);

        //the array JSONs

        List<String> KnownAs = SandList(SandName.getJSONArray(Also_Known_As));
        List<String> SandIngredients = SandList(SandInfo.getJSONArray(Ingredients));

        sandwich = new Sandwich(SandMainName, KnownAs, SandOrigin, SandDescription, SandIngredients, SandImage);
        return sandwich;

    }
    //Method for Looping thru the two array lists
    private static List<String> SandList(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i <jsonArray.length() ; i++) {
            stringList.add(jsonArray.getString(i));
        }
            return stringList;
        }
    }





//json objects:  name, mainName, placeOfOrigin, description, image
//json arrays:  alsoKnownAs, ingredients

// {\"name\":{\"mainName\":\"Bosna\",\
// "alsoKnownAs\":[\"Bosner\"]},
// \"placeOfOrigin\":\"Austria\",\
// "description\":
//"image\":\"https://upload.wikimedia.org/wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg\",\"" +
//"ingredients\":[\"White bread\",\"Bratwurst\",\"Onions\",\"Tomato ketchup\",\"Mustard\",\"Curry powder\"]}
