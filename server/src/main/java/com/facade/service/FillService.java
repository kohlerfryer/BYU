package com.familymap;

import com.familymap.RegisterRequestBody;
import com.familymap.FamilyMapService;
import com.familymap.DataGenerator;
import com.familymap.Person;
import com.familymap.User;
import com.familymap.UserAccess;
import com.familymap.Event;
import com.familymap.EventAccess;
import com.familymap.PersonAccess;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.net.URLConnection;
import java.lang.NullPointerException;
import com.google.gson.Gson;


public class FillService extends FamilyMapService{

    UserAccess userAccess;
    EventAccess eventAccess;
    PersonAccess personAccess;
    DataGenerator dataGenerator;

    public FillService(){
        super();
        this.userAccess = new UserAccess(this.dbConnection);
        this.eventAccess = new EventAccess(this.dbConnection);
        this.personAccess = new PersonAccess(this.dbConnection);
        this.dataGenerator = new DataGenerator();
    }

    public FillResponseBody fill(FillRequestBody requestBody){

        FillResponseBody responseBody;
        try{
            requestBody.validate(this.userAccess);
            
            String personId = Util.getPersonIdFromUser(requestBody.getUsername())
            Util.clearPersonData(person.getId());
            dataGenerator.generatePersonData(person, requestBody.getGenerationCount(), 2017);

            ancestorIds = personAccess.getAncestorIds(person.getId());
            ancestorsAdded = ancestorIds.size();

            //get number of added events
            ArrayList<Event> newlyAddedEvents = eventAccess.get("person_id", "IN", Util.arrayListToString(ancestorIds));
            System.out.println(Util.arrayListToString(ancestorIds));
            int eventsAdded = newlyAddedEvents.size();

            
            responseBody = new FillResponseBody(ancestorsAdded,eventsAdded);
            //System.out.println(responseBody);
        }catch(InvalidRequestException e){
            responseBody = new FillResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new FillResponseBody("Missing parameters");
            e.printStackTrace();
        }
        return responseBody;

    }

}
