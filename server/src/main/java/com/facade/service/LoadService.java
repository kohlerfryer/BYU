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
import com.familymap.LoadEvent;
import com.familymap.LoadPerson;
import com.familymap.LoadUser;

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
        boolean success = true;
        try{
            //requestBody.validate();
            ArrayList<LoadPerson> loadPersonArray = requestBody.getLoadPersons();
            ArrayList<LoadEvent> loadEventArray = requestBody.getLoadEvents();
            ArrayList<LoadUser> loadUserArray = requestBody.getLoadUsers();
            int personsCreated= 0;
            //public Person create(String firstName, String lastName, String gender, String fatherId, String motherId, String spouseId){

            for (LoadPerson loadPerson : loadPersonArray) {
                personAccess.create(
                    Util.generateRandomString(),
                    loadPerson.getDescendant(),
                    loadPerson.getFirstName(),
                    loadPerson.getLastName(),
                    loadPerson.getGender(),
                    loadPerson.getFather(),
                    loadPerson.getMother(),
                    loadPerson.getSpouse()
                );
                personsCreated++;
            }

            //(String latitude, String longitude, String country, String city, String type, String year, String personId){
            int eventsCreated = 0;
            for (LoadEvent loadEvent : loadEventArray) {
                personAccess.create(
                    Util.generateRandomString(),
                    loadEvent.getEventID(),
                    loadEvent.getLatitude(),
                    loadEvent.getLongitude(),
                    loadEvent.getCountry(),    
                    loadEvent.getCity(),
                    loadEvent.getYear(),
                    loadEvent.getPersonID()                                                                            
                );
                eventsCreated++;
            }
            //public User create(String username, String email, String personId, String password){
            int usersCreated = 0;
            for (LoadUser loadUser : loadUserArray) {
                userAccess.create(
                    Util.generateRandomString(),
                    loadUser.getUserName(),
                    loadUser.getEmail(),
                    loadUser.getFirstName(),
                    loadUser.getLastName(),
                    loadUser.getGender(),
                    loadUser.getPassword()
                );
                usersCreated++;
            }
            responseBody = new LoadResponseBody("Successfully added "+ personsCreated +" users and "+ eventsCreated +" persons and "+ usersCreated +" events to the database.", success);
        }catch(NullPointerException e){
            success = false;
            responseBody = new LoadResponseBody("missing parameters", success);
            e.printStackTrace();
        }
        return responseBody;

    }

}
