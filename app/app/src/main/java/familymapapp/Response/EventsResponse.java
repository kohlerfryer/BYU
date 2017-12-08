package familymapapp.Response;


import java.util.ArrayList;

import familymapapp.Modal.Event;

/**
 * Created by programmer on 11/29/17.
 */

public class EventsResponse {

    ArrayList<Event> data;

    public EventsResponse(ArrayList<Event> data){
        this.data = data;
        //parse json and get response;
    }

    public ArrayList<Event> getEvents(){
        return data;
    }
}
