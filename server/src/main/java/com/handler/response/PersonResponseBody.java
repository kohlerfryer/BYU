package com.familymap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class PersonResponseBody{
    private String descendant;
    private String personID;
    private String firstName;
    private String lastname;
    private String gender;
    private String father;
    private String mother;
    private String spouse;
    private String message;
    private boolean failure;
    private Gson gson;

    PersonResponseBody(String descendant, String personID, String firstName, String lastname, String gender, String father, String mother, String spouse){
        this.descendant = descendant;
        this.personID = personID;
        this.firstName = firstName;
        this.lastname = lastname;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
        this.gson = new Gson();
    }
    PersonResponseBody(String message){
        this.message = message;
        this.failure = true;
    }

    public String toJsonString(){
        return this.gson.toJson(this.toJson());
    }

    public JsonObject toJson(){
        JsonObject response = new JsonObject();
        if(!this.failure){
            response.addProperty("descendant", this.descendant);
            response.addProperty("personID", this.personID);
            response.addProperty("firstName", this.firstName);
            response.addProperty("lastname", this.lastname);
            response.addProperty("gender", this.gender);
            response.addProperty("father", this.father);
            response.addProperty("mother", this.mother);
            response.addProperty("spouse", this.spouse);
        }
        else{
            response.add("message", new JsonPrimitive(this.message));
        }
        return response;
    }

}
