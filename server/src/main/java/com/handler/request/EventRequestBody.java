package com.familymap;

import java.util.ArrayList;

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



    public void validate(AuthenticationAccess authenticationAccess, EventAccess eventAccess) throws InvalidRequestException{
        RequestBodyHelper.validateAuthentication(this.token);
        if(this.eventId != null){
            System.out.println("asdfasfadf");
            ArrayList<Authentication> authenticationList = authenticationAccess.get("token", "=", this.token);
            Authentication authentication = authenticationList.get(0);
            ArrayList<Event> eventList = eventAccess.get("id", "=", this.eventId);
            if(eventList.size() == 0) throw new InvalidRequestException("Event does not exist");
            Event event = eventList.get(0);
            String descendant = event.getDescendant();
            String userId = authentication.getUserId();
            System.out.println(descendant);
            System.out.println(userId);
            if(!descendant.equals(userId)) throw new InvalidRequestException("Event does not belong to user");
        }
    }
    public void validate() throws InvalidRequestException{
        RequestBodyHelper.validateAuthentication(this.token);
    }
}