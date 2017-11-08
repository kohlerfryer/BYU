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
        PersonsResponseBody responseBody;

        try{
            requestBody.validate();

            ArrayList<PersonResponseBody> responseBodies = new ArrayList<PersonResponseBody>();
            ArrayList<Authentication> authenticationList = authenticationAccess.get("token", "=", requestBody.getToken());
            Authentication authentication = authenticationList.get(0);
            // ArrayList<Person> personList = personAccess.get("descendant", "=", authentication.getUserId());
            // Person currentPerson = personList.get(0);

            // ArrayList<String> ancestorIds = personAccess.getAncestorIds(currentPerson.getId());            
            // ArrayList<Person> ancestors = personAccess.get("id", "IN", ancestorIds);

            ArrayList<Person> ancestors = personAccess.get("descendant", "=", authentication.getUserId());

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