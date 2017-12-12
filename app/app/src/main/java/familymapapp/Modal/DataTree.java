package familymapapp.Modal;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by kittykatt on 12/9/17.
 */

public class DataTree {
    //person_id -> databranch
    public static HashMap<String, DataBranch> branches;
    private static DataTree tree = null;
    //public for two-way data binding
    public static String[] types = {"death", "birth", "marriage", "baptism"};
    //"death": 0
    private static LinkedHashMap<String, Integer> activeEventTypes;

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
        activeEventTypes = new LinkedHashMap<>();
        for (Event event : events) {
            activeEventTypes.put(event.getEventType(), 1);
            if (!branches.containsKey(event.getPersonId())) {
                branches.put(event.getPersonId(), new DataBranch(event.getPersonId()));
            }
            branches.get(event.getPersonId()).addEvent(event);
        }
    }

    public ArrayList<Event> getFilteredEvents() {
        ArrayList<Event> combinedEvents = new ArrayList<Event>();
        for (HashMap.Entry<String, DataBranch> entry : this.branches.entrySet()) {
            //Integer key = entry.getKey();
            DataBranch branch = entry.getValue();
            //change this below
            ArrayList<Event> personEvents = branch.getFilteredEvents(getActiveTypes());
            combinedEvents.addAll(personEvents);
        }
        return combinedEvents;
    }

    public ArrayList<Event> getFilteredPersonEvents(String person) {
        return branches.get(person).getFilteredEvents(getAllTypes());
    }

    public Person getPerson(String personId) {
        return branches.get(personId).getPerson();
    }

    public int getActiveEventTypeSize(){
        return activeEventTypes.size();
    }

    public void setActiveEventType(String type, Integer i){
        activeEventTypes.put(type, i);
    }

    public int getActiveEventType(String index){
        return activeEventTypes.get(index);
    }

    public String[] getActiveTypes(){
        String[] types = new String[activeEventTypes.size()];
        int index = 0;
        for (HashMap.Entry<String, Integer> entry : activeEventTypes.entrySet()) {
            String key = entry.getKey();
            Integer active = entry.getValue();
            if(active == 1){
                types[index++] = key;
            }
        }
        return types;
    }

    public String[] getAllTypes(){
        String[] types = new String[activeEventTypes.size()];
        int index = 0;
        for (HashMap.Entry<String, Integer> entry : activeEventTypes.entrySet()) {
            types[index++] = entry.getKey();
        }
        return types;
    }

    public String getActiveEventTypeKey(int index){
        int counter = 0;
        String key = "";
        for (HashMap.Entry<String, Integer> entry : activeEventTypes.entrySet()) {
            if(counter == index){
                key = entry.getKey();
            }
            counter++;
        }
        return key;
    }

    public ArrayList<Person> getFamily(String personId, String... filters) {
        ArrayList<Person> combinedPersons = new ArrayList<Person>();
        if(branches.get(personId) != null){
            if(getPersonsFather(personId) != null){
                combinedPersons.add(getPersonsFather(personId));
            }
            if(getPersonsMother(personId) != null){
                combinedPersons.add(getPersonsMother(personId));
            }
            if(getPersonsSpouse(personId) != null){
                combinedPersons.add(getPersonsSpouse(personId));
            }
            if(getPersonsChildren(personId) != null){
                combinedPersons.addAll(getPersonsChildren(personId));
            }
        }
        return combinedPersons;
    }

    private Person getPersonsFather(String personId) {
        return branches.get(personId).getPersonLeaf().getFather();
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
            if(parentId.equals(potentialChildFatherId) || parentId.equals(potentialChildMotherId)){
                Person child = getPerson(potentialChildId);
                //child.metaData.put("type", "child");
                children.add(child);
            }
        }
        return children;
    }

    public Event getPersonsEarliestEvent(String personId){
        Person person = getPerson(personId);
        Event event = null;
        if(person != null){
            event = branches.get(person.getId()).getEarliestEvent();
        }
        return event;
    }

//    public Event getPersonsSpouseEarliestEvent(String personId){
//        ArrayList<LatLng> coordinates = new ArrayList<LatLng>();
//        Person person = getPersonsSpouse(personId);
//        if(person != null && person.getSpouseId() != null){
//            coordinates.addAll(getPersonsEarliestEvent(person.getSpouseId()));
//        }
//        return coordinates;
//    }

}




