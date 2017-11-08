package com.familymap;

import java.util.ArrayList;

public class LoadUser{
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;

    LoadUser(String userName, String password, String email, String firstName, String lastName, String gender, String personID){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public void validate(UserAccess userAccess) throws InvalidRequestException{
        ArrayList<User> userList = userAccess.get("username", "=", this.userName);
        if(userList.size() > 0) throw new InvalidRequestException("username taken");
        this.validateInitialization();
        if(this.gender!= null && !this.gender.equals("m") && !this.gender.equals("f")) throw new InvalidRequestException("invalid gender type");
    }

    public void validateInitialization() throws InvalidRequestException{
        if(
            this.userName == null
            ||
            this.password == null
            ||
            this.email == null
            || 
            this.firstName == null
            ||
            this.lastName == null
            ||
            this.gender == null
            || 
            this.personID == null
        )throw new InvalidRequestException("Missing parameters");
    }

    public String getUserName(){
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
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
    public String getPersonID(){
        return this.personID;
    }
}