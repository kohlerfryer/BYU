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

        boolean success = true;
        FillResponseBody responseBody;
        try{
            requestBody.validate(this.userAccess);
            this.clearPersonInfoForUser(requestBody.getUserName());
            User user = getUser(requestBody.getUserName());
            dataGenerator.generatePersonData(user, requestBody.getGenerationCount(), 2017, requestBody.getUserName());
            ArrayList<Person> personsList = personAccess.get("descendant", "=", requestBody.getUserName());
            ArrayList<Event> eventsList = eventAccess.get("descendant", "=", requestBody.getUserName());
            // ArrayList<String> ancestorIds = personAccess.getAncestorIds(person.getId());
            // ArrayList<Event> newlyAddedEvents = eventAccess.get("person_id", "IN", ancestorIds);

            responseBody = new FillResponseBody("Successfully added "+ personsList.size() +" persons and "+ eventsList.size() +" events to the database.", success);
        }catch(InvalidRequestException e){
            success = false;
            responseBody = new FillResponseBody(e.getMessage(), success);
        }catch(NullPointerException e){
            success = false;
            responseBody = new FillResponseBody("Missing parameters", success);
            e.printStackTrace();
        }
        return responseBody;

    }

    private User getUser(String userId){
        ArrayList<User> userList = this.userAccess.get("username", "=", userId);
        return userList.get(0);
    }

    private void clearPersonInfoForUser(String userId){
        this.clearUserData(userId);
    }

    private void clearUserData(String username){
        // ArrayList<String> ancestorIds = this.personAccess.getAncestorIds(personId);
        // int ancestorsAdded = ancestorIds.size();
        // this.eventAccess.delete("person_id", "IN", ancestorIds);
        // this.eventAccess.delete("person_id", "=", personId);
        // this.personAccess.delete("id", "IN", ancestorIds);
        this.eventAccess.delete("descendant", "=", username);
        this.personAccess.delete("descendant", "=", username);
    }


}
