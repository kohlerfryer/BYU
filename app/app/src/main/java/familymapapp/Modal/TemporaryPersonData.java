package familymapapp.Modal;

import familymapapp.Modal.Event;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by programmer on 11/29/17.
 */
//TODO MAKE A MODAL SHARED FOLDER
public class TemporaryPersonData {


    private ArrayList<Event> events;
    private static TemporaryPersonData data = null;

    protected TemporaryPersonData() {
        // Exists only to defeat instantiation.
    }

    public static TemporaryPersonData getInstance() {
        if(data == null) {
            data = new TemporaryPersonData();
        }
        return data;
    }

    public void setEvents(ArrayList<Event> events){
        this.events = events;
    }

    public ArrayList<Event> getEvents(){

        return this.events;
    }

    public ArrayList<Event> getPersonEvents(String personId){
        ArrayList<Event> personEvents = new ArrayList<Event>();
        for(Event event : this.events){
            if(event.getPersonId().equals(personId)){
                personEvents.add(event);
            }
        }
        return personEvents;
    }

}
