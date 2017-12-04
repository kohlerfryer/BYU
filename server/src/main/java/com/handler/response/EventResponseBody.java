package com.familymap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class EventResponseBody{
    private String descendant;
    private String eventID;
    private String latitude;
    private String longitude;
    private String country;
    private String city;
    private String eventType;
    private String year;
    private String message;
    private String personId;
    private Gson gson;

    EventResponseBody(String descendant, String eventID, String latitude, String longitude, String country, String city, String eventType, String year, String personId){
        this.descendant = descendant;
        this.eventID = eventID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.personId = personId;
        this.gson = new Gson();
    }
    EventResponseBody(String message){
        this.message = message;
        this.gson = new Gson();
    }

    public String toJsonString(){
        return this.gson.toJson(this.toJson());
    }

    public JsonObject toJson(){
        JsonObject response = new JsonObject();
        if(this.wasSuccessfull()){
            response.addProperty("id", this.eventID);
            response.addProperty("latitude", this.latitude);
            response.addProperty("longitude", this.longitude);
            response.addProperty("country", this.country);
            response.addProperty("city", this.city);
            response.addProperty("type", this.eventType);
            response.addProperty("year", this.year);
            response.addProperty("personId", this.personId);
            response.addProperty("descendant", this.descendant);

        }
        else{
            response.add("message", new JsonPrimitive(this.message));
        }
        return response;
    }

    public boolean wasSuccessfull(){
        return (this.message == null);
    }

}
