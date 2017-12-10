package com.familymap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class PersonResponseBody{
    private String descendant;
    private String personID;
    private String firstName;
    private String lastName;
    private String gender;
    private String father;
    private String mother;
    private String spouse;
    private String message;
    private Gson gson;

    PersonResponseBody(String descendant, String personID, String firstName, String lastName, String gender, String father, String mother, String spouse){
        this.descendant = descendant;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
        this.gson = new Gson();
    }
    PersonResponseBody(String message){
        this.message = message;
        this.gson = new Gson();
    }

    public String toJsonString(){
        return this.gson.toJson(this.toJson());
    }

    public JsonObject toJson(){
        JsonObject response = new JsonObject();
        if(this.wasSuccessfull()){
            response.addProperty("descendant", this.descendant);
            response.addProperty("id", this.personID);
            response.addProperty("firstName", this.firstName);
            response.addProperty("lastName", this.lastName);
            response.addProperty("gender", this.gender);
            response.addProperty("fatherId", this.father);
            response.addProperty("motherId", this.mother);
            response.addProperty("spouseId", this.spouse);
        }
        else{
            response.addProperty("message",this.message);
        }
        return response;
    }

    public boolean wasSuccessfull(){
        return (this.message == null);
    }

}
