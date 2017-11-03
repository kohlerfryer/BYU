package com.familymap;

import com.familymap.ResponseBodyWrapper;
import com.familymap.RegisterRequestBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.net.URLConnection;

public class RegisterService extends FamilyMapService{

    UserAccess userAccess;
    AuthenticationAccess authenticationAccess;
    PersonAccess personAccess;

    public RegisterService(){
        super();
        UserAccess userAccess = new UserAccess(this.db);
        AuthenticationAccess authenticationAccess = new AuthenticationAccess(this.db);
        PersonAccess personAccess = new PersonAccess(this.db);
        //implement singleton pattern instead with db
    }

    // public boolean validRequestBody(JsonObject request){
    //     if(requestBody.get("username").getAsString() == null)return false;
    //     if(requestBody.get("password").getAsString()  == null)return false;
    //     if(requestBody.get("username").getAsString()  == null)return false;
    //     if(requestBody.get("firstName").getAsString()  == null)return false;
    //     if(requestBody.get("lastName").getAsString()  == null)return false;
    //     if(requestBody.get("gender").getAsString()  == null)return false;
    //     ArrayList<User> userList = this.userAccess.get("username", "=", requestBody.get("username"));
    //     if(userList.size() > 0)return false;
    // }

    public JsonObject generateSuccessResponseBody(String authToken, String userName, String personId){
        RegisterRequestBody requestBody = gson.fromJson(requestBody, RegisterRequestBody.class); 
        JsonObject responseBody = new JsonObject();
        responseBody.add("authToken", new JsonPrimitive(authToken));
        responseBody.add("userName", new JsonPrimitive(userName));
        responseBody.add("personID", new JsonPrimitive(personId));
        return responseBody;
    }

    public JsonObject generateErrorResponseBody(String message){
        JsonObject responseBody = new JsonObject();
        responseBody.add("message", message);
        return responseBody;    
    }

    public ResponseBodyWrapper register(RegisterRequestBody requestBody) throws InvalidRequestException{

        JsonObject responseBody;
        boolean success = true;
        try{
            // if(this.validateRequestBody(requestBody)){
            //     throw new InvalidRequestException("Invalid parameters");
            // }
            
            Person person = this.personAccess.create(requestBody.getFirstName(), requestBody.getLastName(), requestBody.getGender());
            User user = this.userAccess.create(requestBody.getUsername(), requestBody.getEmail(), person.getId(), requestBody.getPassword());
            Authentication authentication = this.authenticationAccess.create(user.getId());

            //PersonAccess.addFalseData(4, person.getId());
            response = this.generateSuccessResponseBody(user, person);
        }catch(InvalidRequestException e){
            response = this.generateErrorResponseBody(e.getMessage());
            success = false;
        }
        return new ResponseBodyWrapper(success, response);

    }

}