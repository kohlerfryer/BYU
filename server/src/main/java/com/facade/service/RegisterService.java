package com.familymap;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class RegisterService extends FamilyMapService{

    private RegisterResponse();
    public RegisterService(DBConnection dbConnection){
        super(dbConnection);
    }

    public void validRequestBody(JsonObject request) throws InvalidRequestException{
        //request property missing
        //username taken
        //throw new exception("invalid parameters")
    }

    public JsonObject generateSuccessResponseBody(String authToken, String userName, String personId){
        return null;
        //JsonObject responseBody = new JsonObject();
        //responseBody.
    }

    public JsonObject generateErrorResponseBody(String message){
        return null;
    }

    public ResponseBodyWrapper register(JsonObject requestBody) throws InvalidRequestException{

        JsonObject responseBody;
        boolean success = true;
        try{
            this.validateRequestBody(requestBody);

            UserAccess userAccess = new UserAccess(this.dbConnection);
            AuthenticationAccess authenticationAccess = new AuthenticationAccess();
            PersonAccess personAccess = new PersonAccess(this.dbConnection);

            String username = requestBody.get("username");
            String password = requestBody.get("password");
            String email = requestBody.get("username");
            String firstName = requestBody.get("firstName");
            String lastName = requestBody.get("lastName");
            String gender = requestBody.get("gender");
            
            Person person = personAccess.create(firstName, lastName, gender);
            User user = userAccess.create(username, email, person.getId(), password);
            Authentication authentication = authenticationAccess.create(user.getId());

            PersonAccess.addFalseData(4, person.getId());
            response = this.generateSuccessResponseBody(user, person);
        }catch(InvalidRequestException e){
            response = this.generateErrorResponseBody(e.getMessage());
            success = false;
        }
        return new ResponseBodyWrapper(success, response);

    }

}