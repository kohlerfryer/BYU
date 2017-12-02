package familymapapp.Response;


import java.util.ArrayList;

import familymapapp.Modal.Event;

/**
 * Created by programmer on 11/29/17.
 */

public class EventsResponse {

    Event[] data;

    public EventsResponse(Event[] data){
        this.data = data;
        //parse json and get response;
    }

    public Event[] getEvents(){
        return data;
    }
}
