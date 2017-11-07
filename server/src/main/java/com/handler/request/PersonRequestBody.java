package com.familymap;

public class PersonRequestBody{
    private String personID;

    PersonRequestBody(String personID){
        this.personID = personID;   
    }

    public String getPersonId(){
        return this.personID;
    }

    public void validate() throws InvalidRequestException{

    }
}