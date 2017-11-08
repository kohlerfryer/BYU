package com.familymap;

import java.util.ArrayList;

public class PersonRequestBody{
    private String personId;
    private String token;

    PersonRequestBody(String personId, String token){
        this.personId = personId;
        this.token = token;
    }
    PersonRequestBody(String token){
        this.token = token;
    }

    public String getPersonId(){
        return this.personId;
    }

    public String getToken(){
        return this.token;
    }

    public void validate(AuthenticationAccess authenticationAccess, PersonAccess personAccess) throws InvalidRequestException{
        RequestBodyHelper.validateAuthentication(this.token);
        if(this.personId != null){
            System.out.println("asdfasfadf");
            ArrayList<Authentication> authenticationList = authenticationAccess.get("token", "=", this.token);
            Authentication authentication = authenticationList.get(0);
            ArrayList<Person> personList = personAccess.get("id", "=", this.personId);
            if(personList.size() == 0) throw new InvalidRequestException("Person does not exist");
            Person person = personList.get(0);
            String descendant = person.getDescendant();
            String userId = authentication.getUserId();
            System.out.println(descendant);
            System.out.println(userId);
            if(!descendant.equals(userId)) throw new InvalidRequestException("Person does not belong to user");
        }
    }
    public void validate() throws InvalidRequestException{
        RequestBodyHelper.validateAuthentication(this.token);
    }
}