package com.familymap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class FillResponseBody{
    private String message;
    private boolean success;
    private Gson gson;

    FillResponseBody(String message, boolean success){
        this.message = message;
        this.success = success;
        gson = new Gson();
    }

    public String toJsonString(){
        JsonObject response = new JsonObject();
        response.addProperty("message", this.message);
        return gson.toJson(response);
    }

}