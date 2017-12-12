package familymapapp.Modal;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class DataTree {

    private static DataTree tree = null;

    public static HashMap<String, Integer> eventFilters;
    public static ArrayList<String> indexForEventFilters;
    public static ArrayList<String> motherSidePersons;
    public static ArrayList<String> fatherSidePersons;

    // personId -> person
    private static HashMap<String, Person> persons;
    // eventId -> event
    private static HashMap<String, Event> events;
    private static Person rootPerson;

    private static final String FATHER_SIDE_FILTER = "father's side";
    private static final String MOTHER_SIDE_FILTER = "mother's side";
    private static final String MALE_EVENT_FILTER = "Male Events";
    private static final String FEMALE_EVENT_FILTER = "Female Events";


    protected DataTree() {
        persons = new HashMap<>();
        events = new HashMap<>();
        eventFilters = new HashMap<>();
        motherSidePersons = new ArrayList<>();
        fatherSidePersons = new ArrayList<>();
        indexForEventFilters = new ArrayList<>();

        eventFilters.put(FATHER_SIDE_FILTER, 1);
        eventFilters.put(MOTHER_SIDE_FILTER, 1);
        eventFilters.put(MALE_EVENT_FILTER, 1);
        eventFilters.put(FEMALE_EVENT_FILTER, 1);
    }

    public static DataTree getInstance() {
        if (tree == null) {
            tree = new DataTree();
        }
        return tree;
    }

    public static void setPersons(ArrayList<Person> disorganizedPersons) {
        for (Person person : disorganizedPersons) {
            DataTree.persons.put(person.getId(), person);
        }

        setFatherSidePersons(rootPerson.getFather());
        setMotherSidePersons(rootPerson.getMother());
    }

    public static void setEvents(ArrayList<Event> disorganizedEvents) {
        for (Event event : disorganizedEvents) {
            DataTree.events.put(event.getId(), event);
            persons.get(event.getPersonId()).addToEventArray(event);
            eventFilters.put(event.getEventType().toLowerCase(), 1);
        }

        for (HashMap.Entry<String, Integer> entry : eventFilters.entrySet()) {
            String key = entry.getKey();
            indexForEventFilters.add(key);
        }

    }

    public static void setRootPerson(Person person){
        DataTree.rootPerson = person;
    }

    public static ArrayList<Event> getFilteredEvents(){
        ArrayList<Event> filteredEvents = new ArrayList<>();
        for (HashMap.Entry<String, Event> entry : events.entrySet()) {
            Event event = entry.getValue();
            if(eventFilters.get(event.getEventType()) == 1 && filterableFromStandardFilters(event)){
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    public static HashMap<String, Person> getPersons(){
        return persons;
    }

    public static ArrayList<Person> getChildren(Person parent){
        ArrayList<Person> children = new ArrayList<>();
        for (HashMap.Entry<String, Person> entry : persons.entrySet()) {
            Person person = entry.getValue();
            if(person.getMotherId() != null && person.getMotherId().equals(parent.getId())){
                children.add(person);
            }
            if(person.getFatherId() != null && person.getFatherId().equals(parent.getId())){
                children.add(person);
            }
        }
        return children;
    }

    public static boolean passesMaleFilter(Event event){
        return (eventFilters.get(MALE_EVENT_FILTER) == 1 && persons.get(event.getPersonId()).getGender() == "M");
    }

    public static boolean passesFemaleFilter(Event event){
        return (eventFilters.get(FEMALE_EVENT_FILTER) == 1 && persons.get(event.getPersonId()).getGender() == "F");
    }

    public static boolean passesGenderFilter(Event event){
        if(eventFilters.get(FEMALE_EVENT_FILTER) != 1 && eventFilters.get(MALE_EVENT_FILTER) != 1)return true;
        return (passesFemaleFilter(event) || passesMaleFilter(event));
    }

    public static boolean passesFamilyTreeFilter(Event event){
        
    }

    public static boolean filterableFromStandardFilters(Event event){
        if(!passesGenderFilter(event))return false;
        if(!passesFamilyTreeFilter)return false;
        return true;

        if(passesMaleFemaleFilter(event)) {
            if(eventFilters.get(FATHER_SIDE_FILTER) == 1 && fatherSidePersons.contains(event.getPersonId())){
                return true;
            }
            else if(eventFilters.get(MOTHER_SIDE_FILTER) == 1 && motherSidePersons.contains(event.getPersonId())){
                return true;
            }
        }
        return false;
    }

    public static void addGenerationIds(Person person, ArrayList<String> list){
        list.add(person.getId());
        if (person.getFather() != null){
            addGenerationIds(person.getFather(), list);
        }
        if (person.getMother() != null){
            addGenerationIds(person.getMother(), list);
        }
    }

    public static void setFatherSidePersons(Person person){
        addGenerationIds(person, fatherSidePersons);
    }

    public static void setMotherSidePersons(Person person){
        addGenerationIds(person, motherSidePersons);
    }

//    public static ArrayList<String> getActiveEventFilters(){
//        return activeEventFilters;
//    }


//    public ArrayList<Event> getFilteredEvents() {
//        ArrayList<Event> combinedEvents = new ArrayList<Event>();
//        for (HashMap.Entry<String, DataBranch> entry : this.branches.entrySet()) {
//            //Integer key = entry.getKey();
//            DataBranch branch = entry.getValue();
//            //change this below
//            ArrayList<Event> personEvents = branch.getFilteredEvents(getActiveTypes());
//            combinedEvents.addAll(personEvents);
//        }
//        return combinedEvents;
//    }
//
//    public ArrayList<Event> getFilteredPersonEvents(String person) {
//        return branches.get(person).getFilteredEvents(getAllTypes());
//    }
//
//    public Person getPerson(String personId) {
//        return branches.get(personId).getPerson();
//    }
//
//    public int getActiveEventTypeSize(){
//        return activeEventTypes.size();
//    }
//
//    public void setActiveEventType(String type, Integer i){
//        activeEventTypes.put(type, i);
//    }
//
//    public int getActiveEventType(String index){
//        return activeEventTypes.get(index);
//    }
//
//    public String[] getActiveTypes(){
//        String[] types = new String[activeEventTypes.size()];
//        int index = 0;
//        for (HashMap.Entry<String, Integer> entry : activeEventTypes.entrySet()) {
//            String key = entry.getKey();
//            Integer active = entry.getValue();
//            if(active == 1){
//                types[index++] = key;
//            }
//        }
//        return types;
//    }
//
//    public String[] getAllTypes(){
//        String[] types = new String[activeEventTypes.size()];
//        int index = 0;
//        for (HashMap.Entry<String, Integer> entry : activeEventTypes.entrySet()) {
//            types[index++] = entry.getKey();
//        }
//        return types;
//    }
//
//    public String getActiveEventTypeKey(int index){
//        int counter = 0;
//        String key = "";
//        for (HashMap.Entry<String, Integer> entry : activeEventTypes.entrySet()) {
//            if(counter == index){
//                key = entry.getKey();
//            }
//            counter++;
//        }
//        return key;
//    }
//
//    public ArrayList<Person> getFamily(String personId, String... filters) {
//        ArrayList<Person> combinedPersons = new ArrayList<Person>();
//        if(branches.get(personId) != null){
//            if(getPersonsFather(personId) != null){
//                combinedPersons.add(getPersonsFather(personId));
//            }
//            if(getPersonsMother(personId) != null){
//                combinedPersons.add(getPersonsMother(personId));
//            }
//            if(getPersonsSpouse(personId) != null){
//                combinedPersons.add(getPersonsSpouse(personId));
//            }
//            if(getPersonsChildren(personId) != null){
//                combinedPersons.addAll(getPersonsChildren(personId));
//            }
//        }
//        return combinedPersons;
//    }
//
//    private Person getPersonsFather(String personId) {
//        return branches.get(personId).getPersonLeaf().getFather();
//    }
//
//    private Person getPersonsMother(String personId) {
//        return branches.get(personId).getPersonLeaf().getMother();
//    }
//
//    private Person getPersonsSpouse(String personId) {
//        return branches.get(personId).getPersonLeaf().getSpouse();
//    }
//
//    private ArrayList<Person> getPersonsChildren(String parentId){
//        ArrayList<Person> children  = new ArrayList<Person>();
//        for (HashMap.Entry<String, DataBranch> entry : this.branches.entrySet()) {
//            DataBranch branch = entry.getValue();
//            String potentialChildFatherId = branch.getPerson().getFatherId();
//            String potentialChildMotherId = branch.getPerson().getMotherId();
//            String potentialChildId = branch.getPerson().getId();
//            if(parentId.equals(potentialChildFatherId) || parentId.equals(potentialChildMotherId)){
//                Person child = getPerson(potentialChildId);
//                //child.metaData.put("type", "child");
//                children.add(child);
//            }
//        }
//        return children;
//    }
//
//    public Event getPersonsEarliestEvent(String personId){
//        Person person = getPerson(personId);
//        Event event = null;
//        if(person != null){
//            event = branches.get(person.getId()).getEarliestEvent();
//        }
//        return event;
//    }

//    public Event getPersonsSpouseEarliestEvent(String personId){
//        ArrayList<LatLng> coordinates = new ArrayList<LatLng>();
//        Person person = getPersonsSpouse(personId);
//        if(person != null && person.getSpouseId() != null){
//            coordinates.addAll(getPersonsEarliestEvent(person.getSpouseId()));
//        }
//        return coordinates;
//    }

}




