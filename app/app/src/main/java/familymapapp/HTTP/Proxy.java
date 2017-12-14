package familymapapp.HTTP;

import android.widget.Toast;

import java.util.function.Consumer;

import familymapapp.Modal.DataTree;
import familymapapp.Modal.Person;
import familymapapp.Response.EventsResponse;
import familymapapp.Response.PersonsResponse;
import familymapapp.Service.EventsService;
import familymapapp.Service.PersonService;
import familymapapp.Service.PersonsService;
import familymapapp.UTIL.Util;
import fryer.kohler.familymapapp.MapFragment;

/**
 * Created by programmer on 12/12/17.
 */

public class Proxy {
    public static String authenticationToken;
    public static String rootPersonId;

    public static boolean syncData(Consumer<String> onSuccess, Consumer<String> onFailure){
        DataTree.clear();
        boolean ready = true;
        if(authenticationToken == null || rootPersonId == null)return !ready;

        Consumer<String> eventSuccess = (data) -> {
            EventsResponse response = (EventsResponse) Util.convertJsonStringToObject(data, EventsResponse.class);
            DataTree.getInstance().setEvents(response.getEvents());
            onSuccess.accept("");

        };

        Consumer<String> personsSuccess = (data) -> {
            PersonsResponse response = (PersonsResponse) Util.convertJsonStringToObject(data, PersonsResponse.class);
            DataTree.getInstance().setPersons(response.getPersons());
            EventsService.get(eventSuccess, onFailure);
        };

        Consumer<String> personSuccess = (data) -> {
            Person person = (Person) Util.convertJsonStringToObject(data, Person.class);
            DataTree.getInstance().setRootPerson(person);
            PersonsService.get(personsSuccess, onFailure);
        };

        PersonService.get(rootPersonId, personSuccess, onFailure);
        return ready;
    }

    public static void logout(){
        authenticationToken = null;
    }
}
