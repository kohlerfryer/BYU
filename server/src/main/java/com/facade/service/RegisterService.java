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
            String hashedPassword = Util.getHash(requestBody.getPassword());
            String personId = Util.generateRandomString();
//    public User create(String username, String email, String firstName, String lastName, String gender, String password, String personId){

            User user = this.userAccess.create(
                requestBody.getUsername(), 
                requestBody.getEmail(), 
                requestBody.getFirstName(), 
                requestBody.getLastName(), 
                requestBody.getGender(), 
                hashedPassword,
                requestBody.getFirstName() + "_" + requestBody.getLastName()
            );
//    public Person create(String id, String firstName, String lastName, String gender, String fatherId, String motherId, String spouseId, String descendant){
            Person person = this.personAccess.create(
                requestBody.getFirstName()+ "_" +requestBody.getLastName(),
                requestBody.getFirstName(), 
                requestBody.getLastName(), 
                requestBody.getGender(), 
                null, 
                null,
                null,
                requestBody.getUsername()
            );
            Authentication authentication = this.authenticationAccess.create(Util.generateRandomString(), requestBody.getUsername());
            dataGenerator.generatePersonData(user, 4, 2017, requestBody.getUsername());
            responseBody = new RegisterResponseBody(authentication.getToken(), requestBody.getUsername(), requestBody.getFirstName() + "_" + requestBody.getLastName());
        }catch(InvalidRequestException e){
            responseBody = new RegisterResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new RegisterResponseBody("Missing parameters");
            e.printStackTrace();
        }
        return responseBody;

    }

}
