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


public class PersonService extends FamilyMapService{

    UserAccess userAccess;
    AuthenticationAccess authenticationAccess;
    PersonAccess personAccess;
    DataGenerator dataGenerator;

    public PersonService(){
        super();
        this.userAccess = new UserAccess(this.dbConnection);
        this.authenticationAccess = new AuthenticationAccess(this.dbConnection);
        this.personAccess = new PersonAccess(this.dbConnection);
        this.dataGenerator = new DataGenerator();
    }

    public PersonResponseBody get(PersonRequestBody requestBody){   
        if(requestBody.getPersonId == null){
            return getAncestors(requestBody);
        }else{
            return getPerson(requestBody);
        }
    }

    public PersonResponseBody getPerson(PersonRequestBody requestBody){
        PersonResponseBody responseBody;
        try{
            requestBody.validate(this.userAccess);
            ArrayList<Person> userList = personAccess.get("username", "=", requestBody.getPersonId());
            Person person = userList.get(0);

            responseBody = new PersonResponseBody(authentication.getToken(), user.getUsername(), user.getPersonId());
        }catch(InvalidRequestException e){
            responseBody = new PersonResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new PersonResponseBody("missing parameters");
            e.printStackTrace();
        }
        return responseBody;
    }

    public PersonsResponseBody getAncestors(PersonRequestBody requestBody){
        PersonResponseBody responseBody;
        try{
            requestBody.validate(this.userAccess);
            ArrayList<Person> userList = personAccess.get("username", "=", requestBody.getPersonId());
            Person person = userList.get(0);

            responseBody = new PersonsResponseBody(authentication.getToken(), user.getUsername(), user.getPersonId());
        }catch(InvalidRequestException e){
            responseBody = new PersonsResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new PersonsResponseBody("missing parameters");
            e.printStackTrace();
        }
        return responseBody;
    }

    public PersonRequestBody(){

    }



}
