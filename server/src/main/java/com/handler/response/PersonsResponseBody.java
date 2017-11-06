package com.familymap;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

public class PersonResponseBody{
    private PersonResponseBody[] responseBodies;


    PersonResponseBody(PersonResponseBody responseBodies){
        this.responseBodies = responseBodies;
    }

    public String toJsonString(){
        JsonObject response = new JsonObject();
        JsonArray dataArray = new JsonArray();
        for(JsonObject responseBody : this.responseBodies){
            dataArray.add(responsebody.getJson());
        }
        response.addProperty("data", dataArray);
        return gson.toJson(response);
    }

}
