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
import com.familymap.PersonsRequestBody;
import com.familymap.PersonsResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.net.URLConnection;
import java.lang.NullPointerException;
import java.util.ArrayList;
import com.google.gson.Gson;


public class PersonsService extends FamilyMapService{

    UserAccess userAccess;
    AuthenticationAccess authenticationAccess;
    PersonAccess personAccess;
    DataGenerator dataGenerator;

    public PersonsService(){
        super();
        this.userAccess = new UserAccess(this.dbConnection);
        this.authenticationAccess = new AuthenticationAccess(this.dbConnection);
        this.personAccess = new PersonAccess(this.dbConnection);
        this.dataGenerator = new DataGenerator();
    }

    public PersonsResponseBody getPersons(PersonRequestBody requestBody){   
        //request body should be hacked-- get personid from auth token and make a body hehe
        PersonsResponseBody responseBody;
        ArrayList<PersonResponseBody> responseBodies;
        try{
            ArrayList<String> ancestorIds = personAccess.getAncestorIds(requestBody.getPersonId());            
            ArrayList<Person> ancestors = personAccess.get("id", "IN", Util.arrayListToString(ancestorIds));

            for(Person person : ancestors){
                responseBodies.add(new PersonResponseBody(
                    person.getDescendant(),
                    person.getId(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getGender(),
                    person.getFatherId(),
                    person.getMotherId(),
                    person.getSpouseId()
                ));
            }
            responseBody = new PersonsResponseBody(responseBodies);
        }catch(InvalidRequestException e){
            responseBody = new PersonsResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new PersonsResponseBody("missing parameters");
            e.printStackTrace();
        }
        return responseBody;
    }

}