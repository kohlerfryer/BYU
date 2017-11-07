package com.familymap;

import com.familymap.RegisterRequestBody;
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
import com.google.gson.Gson;


public class RegisterService extends FamilyMapService{

    UserAccess userAccess;
    AuthenticationAccess authenticationAccess;
    PersonAccess personAccess;
    DataGenerator dataGenerator;

    public RegisterService(){
        super();
        this.userAccess = new UserAccess(this.dbConnection);
        this.authenticationAccess = new AuthenticationAccess(this.dbConnection);
        this.personAccess = new PersonAccess(this.dbConnection);
        this.dataGenerator = new DataGenerator();
    }

    public RegisterResponseBody register(RegisterRequestBody requestBody){

        RegisterResponseBody responseBody;
        try{
            requestBody.validate(this.userAccess);
            User user = this.userAccess.create(
                Util.generateRandomString(),
                requestBody.getUsername(), 
                requestBody.getEmail(), 
                requestBody.getFirstName(), 
                requestBody.getLastName(), 
                requestBody.getGender(), 
                requestBody.getPassword()
            );
            Person person = this.personAccess.create(
                Util.generateRandomString(),
                user.getUsername(),
                requestBody.getFirstName(), 
                requestBody.getLastName(), 
                requestBody.getGender(), 
                null, 
                null, 
                user.getId()
            );
            Authentication authentication = this.authenticationAccess.create(Util.generateRandomString(), user.getId());
            dataGenerator.generatePersonData(person, 4, 2017);
            responseBody = new RegisterResponseBody(authentication.getToken(), user.getUsername(), person.getId());
        }catch(InvalidRequestException e){
            responseBody = new RegisterResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new RegisterResponseBody("Missing parameters");
            e.printStackTrace();
        }
        return responseBody;

    }

}
