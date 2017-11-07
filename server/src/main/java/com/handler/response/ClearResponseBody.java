package com.familymap;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.Gson;

public class ClearResponseBody{
    private String message;
    private boolean success;
    private Gson gson;

    ClearResponseBody(String message, boolean success){
        this.message = message;
        this.success = success;
        this.gson = new Gson();
    }

    public String toJsonString(){
        JsonObject response = new JsonObject();
        response.addProperty("message", this.message);
        return gson.toJson(response);
    }

    public boolean wasSuccessfull(){
        return this.success;
    }

}