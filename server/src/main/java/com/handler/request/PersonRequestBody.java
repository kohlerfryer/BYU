package com.familymap;

public class PersonRequestBody{
    private String personID;

    personID(String personID){
        this.personID = personID;   
    }

    public String getPersonId(){
        return this.personID;
    }
}