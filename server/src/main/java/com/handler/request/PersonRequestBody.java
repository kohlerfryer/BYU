package com.familymap;


public class PersonRequestBody{
    private String username;
    private String token;

    PersonRequestBody(String username, String token){
        this.username = username;
        this.token = token;
    }
    PersonRequestBody(String token){
        this.token = token;
    }

    public String getUsername(){
        return this.username;
    }

    public String getToken(){
        return this.token;
    }

    public void validate() throws InvalidRequestException{
            RequestBodyHelper.validateAuthentication(this.token);
    }
}