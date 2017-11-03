package com.familymap;

public class ResponseBodyWrapper{
    private boolean successResponse;
    private JsonObject responseBody;
    
    public ResponseBodyWrapper(boolean successResponse, JsonObject responseBody){
        this.successResponse = successResponse;
        this.responseBody = responseBody;
    }

    public String getResponseBody(){
        return responseBody.toString();
    }
    public boolean responseTypeSuccessfull(){
        return this.successResponse;
    }

}