package com.familymap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import com.familymap.PersonResponseBody;


public class PersonsResponseBody{
    private ArrayList<PersonResponseBody> responseBodies;
    private String message;
    private Gson gson;

    PersonsResponseBody(ArrayList<PersonResponseBody> responseBodies){
        this.gson = new Gson();
        this.responseBodies = responseBodies;
    }
    PersonsResponseBody(String message){
        this.gson = new Gson();
        this.message = message;
    }

    public String toJsonString(){
        JsonObject response = new JsonObject();
        if(this.wasSuccessfull()){
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

    public boolean wasSuccessfull(){
        return (this.message == null);
    }

}
