package familymapapp.Modal;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import familymapapp.Service.PersonService;
import familymapapp.UTIL.Util;

/**
 * Created by kittykatt on 12/9/17.
 */

public class DataBranch{
    //person associated with branch
    PersonLeaf personLeaf;
    //associated persons events
    //eventType -> event
    HashMap<String, EventLeaf> eventLeafs;

    public DataBranch(String personId){
        this.eventLeafs = new HashMap<String, EventLeaf>();
        this.loadPerson(personId);
    }

    public void addEvent(Event event){
        //Log.d("debug", "adding event");
        this.eventLeafs.put(event.getEventType().toLowerCase(), new EventLeaf(event));
        //Log.d("debug", this.eventLeafs.get(event.getEventType().toLowerCase()).getLeaf().getCity());
    }

//    public setPerson(Person person) {
//        this.personLeaf = new PersonLeaf(person);
//    }

    public ArrayList<Event> getFilteredEvents(String ... types){
        ArrayList<Event> combinedEvents = new ArrayList<Event>();
        for(String type : types){
            Log.d("debug", "type: " + type);
            if(eventLeafs.get(type) != null){
                combinedEvents.add(eventLeafs.get(type).getLeaf());
            }
        }
        return combinedEvents;
    }


//    public Person getFilteredPerson(String ... strings){
//
//    }

    public PersonLeaf getPersonLeaf(){
        return this.personLeaf;
    }

    public Person getPerson(){
        return this.personLeaf.getLeaf();
    }


    private void loadPerson(String personId){
        Consumer<String> success = (data) -> {
            //Log.d("debug", data);
            Person person = (Person) Util.convertJsonStringToObject(data, Person.class);
            this.personLeaf = new PersonLeaf(person);

        };

        Consumer<String> failure = (data) -> {
            //Toast.makeText(this, Util.getValueFromJson(data, "message"), 30000).show();
        };
        PersonService.get(personId, success, failure);
    }
}
