package com.familymap;

import java.util.ArrayList;

public class LoadEvent{
    private String descendant;
    private String eventID;
    private String personID;
    private String latitude;
    private String longitude;
    private String country;
    private String city;
    private String eventType;
    private String year;

    LoadEvent(String descendant, String eventID, String personID, String latitude, String longitude, String country, String city, String eventType, String year){
        this.descendant = descendant;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;        
    }

    public void validate(UserAccess userAccess) throws InvalidRequestException{
       this.validateInitialization();
    }

    public void validateInitialization() throws InvalidRequestException{
        if(
            this.descendant == null
            ||
            this.eventID == null
            ||
            this.personID == null
            || 
            this.latitude == null
            ||
            this.longitude == null
            || 
            this.country == null
            || 
            this.city == null
            || 
            this.eventType == null
            || 
            this.year == null
        )throw new InvalidRequestException("Missing parameters");
    }

    public String getDescendant(){
        return this.descendant;
    }
    public String getEventID(){
        return this.eventID;
    }
    public String getPersonID(){
        return this.personID;
    }
    public String getLatitude(){
        return this.latitude;
    }
    public String getLongitude(){
        return this.longitude;
    }
    public String getCountry(){
        return this.country;
    }
    public String getCity(){
        return this.city;
    }
    public String getEventType(){
        return this.eventType;
    }
    public String getYear(){
        return this.year;
    }

}

