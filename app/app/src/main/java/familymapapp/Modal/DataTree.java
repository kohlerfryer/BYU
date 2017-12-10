package familymapapp.Modal;

import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kittykatt on 12/9/17.
 */

public class DataTree {
    //person_id -> databranch
    private static HashMap<String, DataBranch> branches;
    private static DataTree tree = null;
    //public for two-way data binding
    public static String[] activeEventTypes = {"death", "birth", "marriage", "baptism"};

    protected DataTree() {
        // Exists only to defeat instantiation.
    }

    public static DataTree getInstance() {
        if (tree == null) {
            tree = new DataTree();
        }
        return tree;
    }

    public static void initialize(ArrayList<Event> events) {
        branches = new HashMap<String, DataBranch>();
        for (Event event : events) {
            //Log.d("debug", "events");
            if (!branches.containsKey(event.getPersonId())) {
                //Log.d("debug", "does not contain key");
                branches.put(event.getPersonId(), new DataBranch(event.getPersonId()));
            }
            branches.get(event.getPersonId()).addEvent(event);
        }
    }

    //    public ArrayList<Event> getPersonEvents(String personId){
//
//    }
//
    public ArrayList<Event> getFilteredEvents(String... filters) {
        ArrayList<Event> combinedEvents = new ArrayList<Event>();
        for (HashMap.Entry<String, DataBranch> entry : this.branches.entrySet()) {
            //Integer key = entry.getKey();
            DataBranch branch = entry.getValue();
            //change this below
            ArrayList<Event> personEvents = branch.getFilteredEvents(filters);
            combinedEvents.addAll(personEvents);
        }
        return combinedEvents;
    }

    public ArrayList<Event> getFilteredPersonEvents(String person, String... filters) {
        return branches.get(person).getFilteredEvents(filters);
    }

    public Person getPerson(String personId) {
        return branches.get(personId).getPerson();
    }

    public ArrayList<Person> getFamily(String personId, String... filters) {
        ArrayList<Person> combinedPersons = new ArrayList<Person>();
        if(branches.get(personId) != null){
            combinedPersons.add(getPersonsFather(personId));
            combinedPersons.add(getPersonsMother(personId));
            combinedPersons.add(getPersonsSpouse(personId));
            combinedPersons.addAll(getPersonsChildren(personId));
        }
        return combinedPersons;
    }

    private Person getPersonsFather(String personId) {
        //Person father;
        //if(branches.get(personId) != null){
            return branches.get(personId).getPersonLeaf().getFather();
        //}
    }

    private Person getPersonsMother(String personId) {
        return branches.get(personId).getPersonLeaf().getMother();
    }

    private Person getPersonsSpouse(String personId) {
        return branches.get(personId).getPersonLeaf().getSpouse();
    }

    private ArrayList<Person> getPersonsChildren(String parentId){
        ArrayList<Person> children  = new ArrayList<Person>();
        for (HashMap.Entry<String, DataBranch> entry : this.branches.entrySet()) {
            DataBranch branch = entry.getValue();
            String potentialChildFatherId = branch.getPerson().getFatherId();
            String potentialChildMotherId = branch.getPerson().getMotherId();
            String potentialChildId = branch.getPerson().getId();
            if(parentId.equals(potentialChildFatherId)){
                children.add(getPerson(potentialChildId));
            }
            else if(parentId.equals(potentialChildMotherId)){
                children.add(getPerson(potentialChildId));
            }
        }
        Log.d("debug", children.size() + " ***************************************************");
        return children;
    }

    //Public ArrayList<Person> getFathersAncestors(String personId){

    //}
//
//    public Person getPersonsMother(String personId){
//
//    }
//
//    public ArrayList<Person> getPersonsChildren(String personId){
//
//    }

}




