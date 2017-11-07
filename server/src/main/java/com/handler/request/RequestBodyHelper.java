package com.familymap;

import java.util.ArrayList;
import java.sql.Timestamp;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RequestBodyHelper{


    public static void validateAuthentication(String token) throws InvalidRequestException{

            final int experiationCeiling = 3600000;

            if(token == null)throw new InvalidRequestException("Missing Auth Token");

            DBConnection dbConnection = DBSingleton.getInstance();
            AuthenticationAccess authenticationAccess = new AuthenticationAccess(dbConnection);
            ArrayList<Authentication> authenticationList = authenticationAccess.get("token", "=", token);

            if(authenticationList.size() == 0)throw new InvalidRequestException("Auth token given does not exist");

            Authentication authentication = authenticationList.get(0);
            Timestamp authBegin = Timestamp.valueOf(authentication.getTimestamp());
            Timestamp authExpire = new Timestamp(authBegin.getTime() + experiationCeiling);

            if(authBegin.after(authExpire))throw new InvalidRequestException("Login expired");

    }

    public static String getBasicError(){
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        response.addProperty("message", "Unkown error occured");
        return gson.toJson(response);
    }
}