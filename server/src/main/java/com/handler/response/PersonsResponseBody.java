package com.familymap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

public class PersonsResponseBody{
    private ArrayList<PersonResponseBody> responseBodies;
    private String message;
    private boolean failure;
    private Gson gson;

    PersonsResponseBody(ArrayList<PersonResponseBody> responseBodies){
        this.responseBodies = responseBodies;
        this.gson = new Gson();
    }
    PersonsResponseBody(String message){
        this.message = message;
        this.failure = true;
    }

    public String toJsonString(){
        JsonObject response = new JsonObject();
        if(!this.failure){
            JsonArray dataArray = new JsonArray();
            for(PersonResponseBody responseBody : this.responseBodies){
                dataArray.add(responseBody.toJson());
            }
            response.add("data", dataArray);
        }
        else{
            response.add("message", new JsonPrimitive(this.message));
        }
        return this.gson.toJson(response);
    }

}
