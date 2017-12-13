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

    public static HashMap<String, Event> getEvents(){
        return events;
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
        return (eventFilters.get(MALE_EVENT_FILTER) == 1 && persons.get(event.getPersonId()).getGender().equals("M"));
    }

    public static boolean passesFemaleFilter(Event event){
        return (eventFilters.get(FEMALE_EVENT_FILTER) == 1 && persons.get(event.getPersonId()).getGender().equals("F"));
    }

    public static boolean passesGenderFilter(Event event){
        if(eventFilters.get(FEMALE_EVENT_FILTER) != 1 && eventFilters.get(MALE_EVENT_FILTER) != 1)return false;
        return (passesFemaleFilter(event) || passesMaleFilter(event));
    }

    public static boolean passesFatherSideFilter(Event event){
        return(eventFilters.get(FATHER_SIDE_FILTER) == 1 && fatherSidePersons.contains(event.getPersonId()));
    }

    public static boolean passesMotherSideFilter(Event event){
        return(eventFilters.get(MOTHER_SIDE_FILTER) == 1 && motherSidePersons.contains(event.getPersonId()));
    }


    public static boolean passesFamilyTreeFilter(Event event){
        if(eventFilters.get(MOTHER_SIDE_FILTER) != 1 && eventFilters.get(FATHER_SIDE_FILTER) != 1)return false;
        return(passesFatherSideFilter(event) || passesMotherSideFilter(event));
    }

    public static boolean filterableFromStandardFilters(Event event){
        if(!passesGenderFilter(event))return false;
        if(!passesFamilyTreeFilter(event))return false;
        return true;
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

    public static ArrayList<Event> searchEvents(String text){
        text = text.toLowerCase();
        ArrayList<Event> searchedEvents = new ArrayList<>();
        for (HashMap.Entry<String, Event> entry : events.entrySet()) {
            Event event = entry.getValue();
            if(event.getCountry().toLowerCase().contains(text) || event.getCity().toLowerCase().contains(text) || event.getYear().toLowerCase().contains(text)){
                searchedEvents.add(event);
            }
        }
        return searchedEvents;
    }

    public static ArrayList<Person> searchPersons(String text){
        text = text.toLowerCase();
        ArrayList<Person> searchedPersons = new ArrayList<>();
        for (HashMap.Entry<String, Person> entry : persons.entrySet()) {
            Person person = entry.getValue();
            person.type = "";
            if(person.getFirstName().toLowerCase().contains(text) || person.getLastName().toLowerCase().contains(text)){
                searchedPersons.add(person);
            }
        }
        return searchedPersons;
    }


}




