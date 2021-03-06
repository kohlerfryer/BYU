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


public class LoginService extends FamilyMapService{

    UserAccess userAccess;
    AuthenticationAccess authenticationAccess;
    PersonAccess personAccess;
    DataGenerator dataGenerator;

    public LoginService(){
        super();
        this.userAccess = new UserAccess(this.dbConnection);
        this.authenticationAccess = new AuthenticationAccess(this.dbConnection);
        this.personAccess = new PersonAccess(this.dbConnection);
        this.dataGenerator = new DataGenerator();
    }

    public LoginResponseBody login(LoginRequestBody requestBody){

        LoginResponseBody responseBody;
        try{
            requestBody.validate(this.userAccess);
            //wrong person yo
            ArrayList<User> userList = this.userAccess.get("username", "=", requestBody.getUsername());
            User user = userList.get(0);
            Authentication authentication = this.authenticationAccess.create(Util.generateRandomString(), requestBody.getUsername());
            responseBody = new LoginResponseBody(authentication.getToken(), requestBody.getUsername(), user.getFirstName() + "_" + user.getLastName());
        }catch(InvalidRequestException e){
            responseBody = new LoginResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new LoginResponseBody("missing parameters");
            e.printStackTrace();
        }
        return responseBody;

    }

}
