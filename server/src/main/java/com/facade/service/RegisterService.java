package com.familymap;

import com.familymap.ResponseBodyWrapper;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.net.URLConnection;

public class RegisterService extends FamilyMapService{

    UserAccess userAccess;
    AuthenticationAccess authenticationAccess;
    PersonAccess personAccess;

    public RegisterService(DBConnection dbConnection){
        super(dbConnection);
        UserAccess userAccess = new UserAccess(this.dbConnection);
        AuthenticationAccess authenticationAccess = new AuthenticationAccess();
        PersonAccess personAccess = new PersonAccess(this.dbConnection);
    }

    public boolean validRequestBody(JsonObject request){
        if(requestBody.get("username").getAsString() == null)return false;
        if(requestBody.get("password").getAsString()  == null)return false;
        if(requestBody.get("username").getAsString()  == null)return false;
        if(requestBody.get("firstName").getAsString()  == null)return false;
        if(requestBody.get("lastName").getAsString()  == null)return false;
        if(requestBody.get("gender").getAsString()  == null)return false;
        ArrayList<User> userList = this.userAccess.get("username", "=", requestBody.get("username"));
        if(userList.size() > 0)return false;
    }

    public JsonObject generateSuccessResponseBody(String authToken, String userName, String personId){
        JsonObject responseBody = new JsonObject();
        responseBody.add("authToken", authToken);
        responseBody.add("userName", userName);
        responseBody.add("personID", personId);
        return responseBody;
    }

    public JsonObject generateErrorResponseBody(String message){
        JsonObject responseBody = new JsonObject();
        responseBody.add("message", message);
        return responseBody;    
    }

    public ResponseBodyWrapper register(JsonObject requestBody) throws InvalidRequestException{

        JsonObject responseBody;
        boolean success = true;
        try{
            if(this.validateRequestBody(requestBody)){
                throw new InvalidRequestException("Invalid parameters");
            }

            String username = requestBody.get("username").getAsString() ;
            String password = requestBody.get("password").getAsString() ;
            String email = requestBody.get("username").getAsString() ;
            String firstName = requestBody.get("firstName").getAsString() ;
            String lastName = requestBody.get("lastName").getAsString() ;
            String gender = requestBody.get("gender").getAsString() ;
            
            Person person = this.personAccess.create(firstName, lastName, gender);
            User user = this.userAccess.create(username, email, person.getId(), password);
            Authentication authentication = this.authenticationAccess.create(user.getId());

            PersonAccess.addFalseData(4, person.getId());
            response = this.generateSuccessResponseBody(user, person);
        }catch(InvalidRequestException e){
            response = this.generateErrorResponseBody(e.getMessage());
            success = false;
        }
        return new ResponseBodyWrapper(success, response);

    }

}