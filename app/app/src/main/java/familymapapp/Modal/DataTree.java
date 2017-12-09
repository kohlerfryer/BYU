package familymapapp.Modal;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kittykatt on 12/9/17.
 */

public class DataTree {
    //person_id -> databranch
    private static HashMap<String, DataBranch> branches;
    private static DataTree tree = null;

    protected DataTree() {
        // Exists only to defeat instantiation.
    }

    public static DataTree getInstance() {
        if(tree == null) {
            tree = new DataTree();
        }
        return tree;
    }

    public static void initialize(ArrayList<Event> events){
        branches = new HashMap<String, DataBranch>();
        for(Event event : events){
            branches.put(event.getPersonId(), new DataBranch(event));
        }
    }

    public ArrayList<Event> getPersonEvents(String personId){

    }

    public ArrayList<Event> getFilteredEvents(String ... filters){

    }

    public Person getPerson(String personId){

    }

    public ArrayList getPersonsFather(String personId){

    }

    public Person getPersonsMother(String personId){

    }

    public ArrayList<Person> getPersonsChildren(String personId){

    }

}




