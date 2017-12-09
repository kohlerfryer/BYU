package familymapapp.Modal;

/**
 * Created by kittykatt on 12/9/17.
 */



public class EventLeaf implements DataLeaf{

    private Event event;

    public EventLeaf(Event event){
        this.event = event;
    }

    public Event getLeaf(){
        return this.event;
    }
    //searches all of event fields for certain value
    public boolean containsValue(String constraint){
        //if(event.getCity.contains(constraint)) return true;
    }
}
