package com.familymap;

/**
* represents a single EventType tuple
*/
public class EventType{

    /** tuples unique identifier in relation */
    private int id;

    /** type of event */
    private String type;

    public EventType(int id, String type){
        this.id = id;
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public int getId(){
        return this.id;
    }

    public void setType(String type){
        this.type = type;
    }
    
}