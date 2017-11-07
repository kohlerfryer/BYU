package com.familymap;


public class EventRequestBody{
    private String eventId;
    private String token;

    EventRequestBody(String eventId, String token){
        this.eventId = eventId;
        this.token = token;
    }
    EventRequestBody(String token){
        this.token = token;
    }

    public String getEventId(){
        return this.eventId;
    }

    public String getToken(){
        return this.token;
    }

    public void validate() throws InvalidRequestException{
            RequestBodyHelper.validateAuthentication(this.token);
    }
}