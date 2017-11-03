package com.familymap;

import com.familymap.RegisterRequestBody;
import com.familymap.FamilyMapService;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.net.URLConnection;
import java.lang.NullPointerException;

public class RegisterService extends FamilyMapService{

    UserAccess userAccess;
    AuthenticationAccess authenticationAccess;
    PersonAccess personAccess;

    public RegisterService(){
        super();
        UserAccess userAccess = new UserAccess(this.dbConnection);
        AuthenticationAccess authenticationAccess = new AuthenticationAccess(this.dbConnection);
        PersonAccess personAccess = new PersonAccess(this.dbConnection);
        //implement singleton pattern instead with db
    }

    public RegisterResponseBody register(RegisterRequestBody requestBody) throws InvalidRequestException{

        RegisterResponseBody responseBody;
        try{
            requestBody.validate();
            Person person = this.personAccess.create(requestBody.getFirstName(), requestBody.getLastName(), requestBody.getGender(), null, null, null);
            User user = this.userAccess.create(requestBody.getUsername(), requestBody.getEmail(), person.getId(), requestBody.getPassword());
            Authentication authentication = this.authenticationAccess.create(user.getId());
            //PersonAccess.addRandomData(4, person.getId());
            responseBody = new RegisterResponseBody(authentication.getToken(), user.getUsername(), person.getId());
        }catch(InvalidRequestException e){
            responseBody = new RegisterResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new RegisterResponseBody("Missing parameters");
        }
        return responseBody;

    }

}