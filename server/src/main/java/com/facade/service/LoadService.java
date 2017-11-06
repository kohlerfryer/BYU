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
            int personsCreated= 0;
            //public Person create(String firstName, String lastName, String gender, String fatherId, String motherId, String spouseId){

            for (LoadPerson loadPerson : loadPersonArray) {
                personAccess.create(
                    loadPerson.getDescendant(),
                    loadPerson.getPersonID(),
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
            for (LoadEvent loadEvent : loadUserArray) {
                personAccess.create(
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
                    loadUser.getUserName(),
                    loadUser.getEmail(),
                    loadUser.getPersonID(),
                    loadUser.getFirstName(),
                    loadUser.getLastName(),
                    loadUser.getGender(),
                    loadUser.getPassword()
                );
                usersCreated++;
            }
            responseBody = new LoadResponseBody("Successfully added "+ personsCreated +" users and "+ eventsCreated +" persons and "+ usersCreated +" events to the database.");
        }catch(InvalidRequestException e){
            responseBody = new LoadResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new LoadResponseBody("missing parameters");
            e.printStackTrace();
        }
        return responseBody;

    }

}
