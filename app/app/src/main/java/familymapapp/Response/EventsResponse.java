package familymapapp.Response;


import java.util.ArrayList;

import familymapapp.Modal.Event;

/**
 * Created by programmer on 11/29/17.
 */

public class EventsResponse {

    Event[] events;

    public EventsResponse(Event[] events){
        this.events = events;
        //parse json and get response;
    }

    public ArrayList<Event> getEvents(){
        return null;
    }
}
