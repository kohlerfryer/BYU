package com.familymap;

public class RegisterResponse{
    private boolean success;
    private String authToken;
    private String userName;
    private String personID;

    RegisterResponse(String authToken, String userName, String personID){
        this.success = true;
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }
    RegisterResponse(String message){
        this.success = false;
        this.message = message;
    }

}