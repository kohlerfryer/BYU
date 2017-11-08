package com.familymap;

import com.familymap.LoginRequestBody;
import com.familymap.FamilyMapService;
import com.familymap.DataGenerator;
import com.familymap.Event;
import com.familymap.User;
import com.familymap.UserAccess;
import com.familymap.Authentication;
import com.familymap.AuthenticationAccess;
import com.familymap.EventAccess;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.net.URLConnection;
import java.lang.NullPointerException;
import java.util.ArrayList;
import com.google.gson.Gson;


public class EventService extends FamilyMapService{

    UserAccess userAccess;
    AuthenticationAccess authenticationAccess;
    EventAccess eventAccess;
    DataGenerator dataGenerator;

    public EventService(){
        super();
        this.userAccess = new UserAccess(this.dbConnection);
        this.authenticationAccess = new AuthenticationAccess(this.dbConnection);
        this.eventAccess = new EventAccess(this.dbConnection);
        this.dataGenerator = new DataGenerator();
    }

    public EventResponseBody getEvent(EventRequestBody requestBody){   
        EventResponseBody responseBody;
        try{
            requestBody.validate(this.authenticationAccess, this.eventAccess);
            ArrayList<Event> eventList = eventAccess.get("id", "=", requestBody.getEventId());
            Event event = eventList.get(0);
//String descendant, String eventID, String latitude, String longitude, String country, String city, String eventType, String year){

            responseBody = new EventResponseBody(
                event.getDescendant(),
                event.getId(),
                event.getLatitude(),
                event.getLongitude(),
                event.getCountry(),
                event.getCity(),
                event.getEventType(),
                event.getYear()
            );
        }catch(InvalidRequestException e){
            responseBody = new EventResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new EventResponseBody("missing parameters");
            e.printStackTrace();
        }
        return responseBody;
    }

}