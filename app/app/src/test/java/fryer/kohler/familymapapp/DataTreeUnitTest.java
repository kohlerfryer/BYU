package fryer.kohler.familymapapp;

import android.provider.ContactsContract;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import familymapapp.Modal.DataTree;
import familymapapp.Modal.Event;
import familymapapp.Modal.Person;
import familymapapp.Request.LoginRequestBody;
import familymapapp.Service.LoginService;
import familymapapp.Util.Settings;
import familymapapp.Util.Util;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DataTreeUnitTest {

    DataTree dataTree;

    Event motherBaptism;
    Event fatherBaptism;
    Event fatherbirth;
    Event rootPersonChristening;

    Person rootPerson;
    Person father;
    Person mother;

    ArrayList<Person> children;

    @Before
    public void setupModel(){
        dataTree = DataTree.getInstance();
        children = new ArrayList<>();

        motherBaptism = new Event("z", "20", "20", "USA","Provo", "BAPTISM", "1965", "becky_fryer", "kohler_fryer");
        fatherBaptism = new Event("a", "20", "20", "USA","Provo", "birth", "1970", "michael_fryer", "kohler_fryer");
        fatherbirth = new Event("b", "20", "20", "USA","Provo", "baptism", "1965", "michael_fryer", "kohler_fryer");
        rootPersonChristening = new Event("c", "20", "20", "USA","Provo", "christening", "1955", "kohler_fryer", "kohler_fryer");

        rootPerson = new Person("kohler_fryer", "kohler", "fryer", "m", "michael_fryer", "becky_fryer", "isabella_fryer", "kohler_fryer");
        father = new Person("michael_fryer", "kohler", "fryer", "m", "", "", "becky_fryer", "kohler_fryer");
        mother = new Person("becky_fryer", "becky", "fryer", "m", "", "", "michael_fryer", "kohler_fryer");

        children.add(rootPerson);

        ArrayList<Event> events = new ArrayList<>();
        events.add(motherBaptism);
        events.add(fatherBaptism);
        events.add(fatherBaptism);
        events.add(rootPersonChristening);

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(rootPerson);
        persons.add(father);
        persons.add(mother);

        dataTree.setRootPerson(rootPerson);
        dataTree.setEvents(events);
        dataTree.setPersons(persons);
    }

    //////////////////////////////////
    //CALCULATE FAMILY RELATIONSHIPS//
    /////////////////////////////////

    @Test
    public void testValidMotherRelationship(){
        Person mother = DataTree.getPersons().get(rootPerson.getMotherId());
        assertEquals(this.mother, mother);
    }

    @Test
    public void testInvalidMotherRelationship(){
        Person mother = DataTree.getPersons().get(this.mother.getMotherId());
        assertEquals(null, mother);
    }

    @Test
    public void testValidFatherRelationship(){
        Person father = DataTree.getPersons().get(rootPerson.getFatherId());
        assertEquals(this.father, father);
    }

    @Test
    public void testInvalidFatherRelationship(){
        Person father = DataTree.getPersons().get(this.father.getMotherId());
        assertEquals(null, father);
    }

    @Test
    public void testValidChildRelationship(){
        ArrayList<Person> children = DataTree.getChildren(mother);
        assertEquals(children.size(), 1);
        assertEquals(children.get(0).getId(), rootPerson.getId());
    }

    @Test
    public void testInvalidChildRelationship(){
        ArrayList<Person> children = DataTree.getChildren(rootPerson);
        assertEquals(children.size(), 0);
    }

    //////////////////////////////////////////////////////////////
    //filtering events according to the current filter settings//
    ////////////////////////////////////////////////////////////

    @Test
    public void testSomeActiveFilters(){
        DataTree.eventFilters.put("birth", 0);
        for(Event event : DataTree.getFilteredEvents()){
            assertNotEquals(event.getEventType(), "birth");
        }
    }

    @Test
    public void testAllInnactiveFilters(){
        DataTree.eventFilters.put("birth", 0);
        DataTree.eventFilters.put("christening", 0);
        DataTree.eventFilters.put("baptism", 0);
        assertEquals(DataTree.getFilteredEvents().size(), 0);
    }

    /////////////////////////////////////////////////////////
    //chronologically sorting a person’s individual events//
    ///////////////////////////////////////////////////////

    @Test
    public void testEventDatesOrder(){
        Event eventIteration = null;
        for(Event event : father.getEvents()){
            if(eventIteration != null){
                assertTrue(Integer.valueOf(event.getYear()) > Integer.valueOf(eventIteration.getYear()));
            }
            eventIteration = event;
        }
    }

    /////////////////////////////////////////////////////////
    //searching for people and events///////////////////////
    ///////////////////////////////////////////////////////

    @Test
    public void testSearchValidPerson(){
        Person mother = DataTree.searchPersons("becky").get(0);
        assertEquals(this.mother, mother);
    }

    @Test
    public void testSearchManyValidPerson(){
        assertEquals(DataTree.searchPersons("fryer").size(), 3);
    }

    @Test
    public void testSearchInvalidPerson(){
        assertEquals(DataTree.searchPersons("***").size(), 0);
    }

    @Test
    public void testSearchValidEvent(){
        Event fatherBaptism = DataTree.searchEvents("1970").get(0);
        assertEquals(this.fatherBaptism, fatherBaptism);
    }

    @Test
    public void testSearchInvalidEvent(){
        assertEquals(DataTree.searchEvents("***").size(), 0);
    }

    @Test
    public void testSearchManyValidEvent(){
        assertEquals(DataTree.searchEvents("Provo").size(), 3);
    }


}
