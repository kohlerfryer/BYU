package familymapapp.Modal;

import java.util.ArrayList;

/**
 * Created by kittykatt on 12/9/17.
 */

public class PersonLeaf implements DataLeaf{

    private Person person;

    public PersonLeaf(Person person){
        this.person = person;
    }

    public Person getLeaf(){
        return this.person;
    }

    public Person getFather(){
        Person father = null;
        if(person.getFatherId() != null){
            father = DataTree.getInstance().getPerson(person.getFatherId());
        }
        return father;
    }

    public Person getMother(){
        Person mother = null;
        if(person.getMotherId() != null){
            mother = DataTree.getInstance().getPerson(person.getMotherId());
        }
        return mother;
    }

    public Person getSpouse(){
        Person spouse = null;
        if(person.getMotherId() != null){
            spouse = DataTree.getInstance().getPerson(person.getSpouseId());
        }
        return spouse;
    }

    //searches all of event fields for certain value
//    public boolean containsValue(String constraint){
//        //if(event.getCity.contains(constraint)) return true;
//    }
}