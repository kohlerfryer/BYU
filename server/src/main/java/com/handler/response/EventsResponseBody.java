package com.familymap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import com.familymap.EventResponseBody;


public class EventsResponseBody{
    private ArrayList<EventResponseBody> responseBodies;
    private String message;
    private Gson gson;

    EventsResponseBody(ArrayList<EventResponseBody> responseBodies){
        this.gson = new Gson();
        this.responseBodies = responseBodies;
    }
    EventsResponseBody(String message){
        this.gson = new Gson();
        this.message = message;
    }

    public String toJsonString(){
        JsonObject response = new JsonObject();
        if(this.wasSuccessfull()){
            JsonArray dataArray = new JsonArray();
            for(EventResponseBody responseBody : this.responseBodies){
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
