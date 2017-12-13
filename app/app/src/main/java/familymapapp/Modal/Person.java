package familymapapp.Modal;

import android.provider.ContactsContract;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

/**
* represents a single Person tuple
*/
public class Person implements DetailsRowDataObject{

    /** tuples unique identifier in relation */
    private String id;

    /** username of person */
    private String descendant;

    /** first name of person */
    private String firstName;

    /** last name of person */
    private String lastName;

    /** gender of person */
    private String gender;

    /** referrs to father tuple of this person */
    private String fatherId;

    /** referrs to mother tuple of this person */
    private String motherId;

    /** referrs to spouse tuple of this person */
    private String spouseId;

    public String type = "child";

    private TreeSet<Event> events = new TreeSet<>(new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            return Integer.valueOf(e1.getYear()) - Integer.valueOf(e2.getYear());
        }
    });

    public Person(){
        this.events = new TreeSet<>(new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                return Integer.valueOf(e1.getYear()) - Integer.valueOf(e2.getYear());
            }
        });
    }

    public Person(String id, String firstName, String lastName, String gender, String fatherId, String motherId, String spouseId, String descendant){
        this.id = id;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.spouseId = spouseId;
    }

    public Person(String id, String descendant, String firstName, String lastName, String gender){
        this.id = id;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    @Override
    public String getFirstRow() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public String getSecondRow() {
        return this.type;
    }

    @Override
    public int getIcon() {
        return android.support.v4.R.drawable.notification_icon_background;
    }

    public String getId(){
        return this.id;
    }
    public String getDescendant(){
        return this.descendant;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getGender(){
        return this.gender;
    }
    public String getFatherId(){
        return this.fatherId;
    }
    public String getMotherId(){
        return this.motherId;
    }
    public String getSpouseId(){
        return this.spouseId;
    }
    public ArrayList<Event> getEvents(){
        ArrayList<Event> eventsArray = new ArrayList<>();
        for(Event event : this.events){
            eventsArray.add(event);
        }
        return eventsArray;
    }
    public Person getMother(){
        Person mother = null;
        if(getMotherId() != null && !getMotherId().equals("")){
            Log.d("debug", "*******************************");
            mother = DataTree.getInstance().getPersons().get(getMotherId());
            mother.type = "mother";
        }
        return mother;
    }
    public Person getFather(){
        Person father = null;
        if(getFatherId() != null && !getFatherId().equals("")){
            Log.d("debug", "*******************************");
            father = DataTree.getInstance().getPersons().get(getFatherId());
            father.type = "father";
        }

        return father;
    }
    public Person getSpouse(){
        Person spouse = null;
        if(getSpouseId() != null && !getSpouseId().equals("")){
            spouse = DataTree.getInstance().getPersons().get(getSpouseId());
            spouse.type = "spouse";
        }
        return spouse;
    }
    public ArrayList<Person> getFamily(){
        ArrayList<Person> family = new ArrayList<>();
        family.addAll(DataTree.getInstance().getChildren(this));
        if(getFather() != null) family.add(getFather());
        if(getMother() != null) family.add(getMother());
        if(getSpouse() != null) family.add(getSpouse());
        return family;
    }

    public void setDescendant(String descendant){
        this.descendant = descendant;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setFatherId(String fatherId){
        this.fatherId = fatherId;
    }
    public void setMotherId(String motherId){
        this.motherId = motherId;
    }
    public void setSpouseId(String spouseId){
        this.spouseId = spouseId;
    }

    public void addToEventArray(Event event){this.events.add(event);}
}