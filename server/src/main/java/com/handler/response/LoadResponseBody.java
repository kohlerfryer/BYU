package com.familymap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class LoadResponseBody{
    private String message;
    private boolean failure;
    private boolean success;
    private Gson gson;

    LoadResponseBody(String message, boolean success){
        this.message = message;
        this.success = success;
        this.gson = new Gson();
    }

    public String toJsonString(){
        JsonObject response = new JsonObject();
        response.addProperty("message", this.message);
        return this.gson.toJson(response);
    }

    public boolean wasSuccessfull(){
        return success;
    }

}