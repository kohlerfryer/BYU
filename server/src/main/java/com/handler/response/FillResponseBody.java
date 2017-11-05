package com.familymap;

public class FillResponseBody{
    private String message;
    private int peopleAdded;
    private int eventsAdded;

    FillResponseBody(int peopleAdded, int eventsAdded){
        this.peopleAdded = peopleAdded;
        this.eventsAdded = eventsAdded;
    }
    FillResponseBody(String message){
        this.message = message;
    }

    public String getAuthToken(){
        return this.authToken;
    }

    public String getUserName(){
        return this.userName;
    }

    public boolean success(){
        return (this.peopleAdded != null);
    }

    //print method :) here



}