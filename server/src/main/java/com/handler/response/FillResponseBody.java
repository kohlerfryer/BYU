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

    public boolean success(){
        return (this.peopleAdded != 0);
    }

    public String toString(){
        return "Successfully added "+ this.peopleAdded +" persons and "+ this.eventsAdded +" events to the database.";
    }

    //print method :) here



}