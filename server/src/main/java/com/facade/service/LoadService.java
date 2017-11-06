package com.familymap;

import com.familymap.LoginRequestBody;
import com.familymap.FamilyMapService;
import com.familymap.DataGenerator;
import com.familymap.Person;
import com.familymap.User;
import com.familymap.UserAccess;
import com.familymap.Authentication;
import com.familymap.AuthenticationAccess;
import com.familymap.PersonAccess;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.net.URLConnection;
import java.lang.NullPointerException;
import java.util.ArrayList;
import com.google.gson.Gson;


public class LoadService extends FamilyMapService{

    UserAccess userAccess;
    AuthenticationAccess authenticationAccess;
    PersonAccess personAccess;
    DataGenerator dataGenerator;

    public LoadService(){
        super();
        this.userAccess = new UserAccess(this.dbConnection);
        this.authenticationAccess = new AuthenticationAccess(this.dbConnection);
        this.personAccess = new PersonAccess(this.dbConnection);
        this.dataGenerator = new DataGenerator();
    }

    public LoadResponseBody load(LoadRequestBody requestBody){

        LoadResponseBody responseBody;
        try{
            requestBody.validate();
            LoadPerson[] loadPersonArray = requestBody.getLoadPerson();
            LoadEvent[] loadUserArray = requestBody.getLoadEvent();
            LoadUser[] loadUserArray = requestBody.getLoadUser();
            for (LoadPerson loadPerson : loadPersonArray) {
                //personAccess.create();
            }
            for (LoadEvent loadEvent : loadUserArray) {
                //personAccess.create();
            }
            for (LoadUser loadUser : loadUserArray) {
                //personAccess.create();
            }
            
            
            responseBody = new LoadResponseBody("Successfully added "+ peopleAdded +" users and "+ eventsAdded +" persons and "+eventsAdded+" events to the database.");
        }catch(InvalidRequestException e){
            responseBody = new LoadResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new LoadResponseBody("missing parameters");
            e.printStackTrace();
        }
        return responseBody;

    }

}
