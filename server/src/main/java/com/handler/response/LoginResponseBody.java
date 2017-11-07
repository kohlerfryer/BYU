package com.familymap;

public class LoginResponseBody{
    private String authToken;
    private String userName;
    private String personID;
    private String message;

    LoginResponseBody(String authToken, String userName, String personID){
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }
    LoginResponseBody(String message){
        this.message = message;
    }

    public String getAuthToken(){
        return this.authToken;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPersonID(){
        return this.personID;
    }

    public String getMessage(){
        return this.message;
    }

    public boolean wasSuccessfull(){
        return (this.authToken != null);
    }



}