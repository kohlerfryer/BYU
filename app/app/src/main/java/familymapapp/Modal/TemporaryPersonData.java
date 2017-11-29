package familymapapp.Modal;

import android.app.usage.UsageEvents;

import java.util.ArrayList;
import java.util.HashMap;
import com.familymap.Event;

/**
 * Created by programmer on 11/29/17.
 */
//TODO MAKE A MODAL SHARED FOLDER
public class TemporaryPersonData {


    private HashMap<String, ArrayList<Event>> events;
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

    public setEvents(HashMap<String, ArrayList<Event>> events){
        this.events = events;
    }

}
