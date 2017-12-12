package familymapapp.Response;

import java.util.ArrayList;

import familymapapp.Modal.Event;
import familymapapp.Modal.Person;

/**
 * Created by kittykatt on 12/11/17.
 */

public class PersonsResponse {

    ArrayList<Person> data;

    public PersonsResponse(ArrayList<Person> data){
        this.data = data;
        //parse json and get response;
    }

    public ArrayList<Person> getPersons(){
        return data;
    }
}
