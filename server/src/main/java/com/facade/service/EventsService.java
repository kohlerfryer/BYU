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
import com.familymap.EventsResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.net.URLConnection;
import java.lang.NullPointerException;
import java.util.ArrayList;
import com.google.gson.Gson;


public class EventsService extends FamilyMapService{

    AuthenticationAccess authenticationAccess;
    EventAccess eventAccess;
    PersonAccess personAccess;
    DataGenerator dataGenerator;

    public EventsService(){
        super();
        this.personAccess = new PersonAccess(this.dbConnection);
        this.authenticationAccess = new AuthenticationAccess(this.dbConnection);
        this.eventAccess = new EventAccess(this.dbConnection);
        this.dataGenerator = new DataGenerator();
    }

    public EventsResponseBody getEvents(EventRequestBody requestBody){   

        EventsResponseBody responseBody;
        ArrayList<EventResponseBody> responseBodies = new ArrayList<EventResponseBody>();
        ArrayList<Authentication> authenticationList = authenticationAccess.get("token", "=", requestBody.getToken());
        Authentication authentication = authenticationList.get(0);
        ArrayList<Person> personList = personAccess.get("descendant", "=", authentication.getUserId());
        Person person = personList.get(0);

        try{
            ArrayList<String> ancestorIds = personAccess.getAncestorIds(person.getId());            
            ArrayList<Event> events = eventAccess.get("person_id", "IN", ancestorIds);

            for(Event event : events){
                responseBodies.add(new EventResponseBody(
                    event.getDescendant(),
                    event.getId(),
                    event.getLatitude(),
                    event.getLongitude(),
                    event.getCountry(),
                    event.getCity(),
                    event.getEventType(),
                    event.getYear()                
                ));
            }
            responseBody = new EventsResponseBody(responseBodies);
        }catch(NullPointerException e){
            responseBody = new EventsResponseBody("missing parameters");
            e.printStackTrace();
        }
        return responseBody;
    }

}