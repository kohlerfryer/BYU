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


public class PersonService extends FamilyMapService{

    UserAccess userAccess;
    AuthenticationAccess authenticationAccess;
    PersonAccess personAccess;
    DataGenerator dataGenerator;

    public PersonService(){
        super();
        this.userAccess = new UserAccess(this.dbConnection);
        this.authenticationAccess = new AuthenticationAccess(this.dbConnection);
        this.personAccess = new PersonAccess(this.dbConnection);
        this.dataGenerator = new DataGenerator();
    }

    public PersonResponseBody getPerson(PersonRequestBody requestBody){   
        PersonResponseBody responseBody;
        try{
            requestBody.validate(this.authenticationAccess, this.personAccess);
            ArrayList<Person> personList = personAccess.get("id", "=", requestBody.getPersonId());
            Person person = personList.get(0);

            responseBody = new PersonResponseBody(
                person.getDescendant(),
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getGender(),
                person.getFatherId(),
                person.getMotherId(),
                person.getSpouseId()
            );
        }catch(InvalidRequestException e){
            System.out.println(e.getMessage());
            responseBody = new PersonResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new PersonResponseBody("missing parameters");
            e.printStackTrace();
        }
        return responseBody;
    }

}