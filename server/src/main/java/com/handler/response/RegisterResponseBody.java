package com.familymap;

import com.google.gson.Gson;

public class RegisterResponseBody{
    private String authToken;
    private String userName;
    private String personID;
    private String message;

    RegisterResponseBody(String authToken, String userName, String personID){
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }
    RegisterResponseBody(String message){
        this.message = message;
    }

    public boolean success(){
        return (this.authToken != null);
    }



}