package com.familymap;

public class LoadPerson{
    private String descendant;
    private String personID;
    private String firstName;
    private String lastname;
    private String gender;
    private String father;
    private String mother;
    private String spouse;


    LoadPerson(String descendant, String personID, String firstName, String lastname, String gender, String father, String mother, String spouse){
        this.descendant = descendant;
        this.personID = personID;
        this.firstName = firstName;
        this.lastname = lastname;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
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
        return this.lastname;
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