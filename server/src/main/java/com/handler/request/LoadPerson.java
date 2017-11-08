package com.familymap;

import java.util.ArrayList;

public class LoadPerson{
    private String descendant;
    private String personID;
    private String firstName;
    private String lastName;
    private String gender;
    private String father;
    private String mother;
    private String spouse;


    LoadPerson(String descendant, String personID, String firstName, String lastName, String gender, String father, String mother, String spouse){
        this.descendant = descendant;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }

    public void validate(UserAccess userAccess) throws InvalidRequestException{
        this.validateInitialization();
        //if(this.gender!= null && !this.gender.equals("m") && !this.gender.equals("f")) throw new InvalidRequestException("invalid gender type");
    }

    public void validateInitialization() throws InvalidRequestException{
        if(
            this.descendant == null
            ||
            this.personID == null
            ||
            this.firstName == null
            ||
            this.lastName == null
            ||
            this.gender == null
        )throw new InvalidRequestException("Missing parameters");
    }

    public String getDescendant(){
        return this.descendant;
    }

    public String getPersonID(){
        return this.personID;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getGender(){
        return this.gender;
    }

    public String getFather(){
        return this.father;
    }

    public String getMother(){
        return this.mother;
    }

    public String getSpouse(){
        return this.spouse;
    }
}