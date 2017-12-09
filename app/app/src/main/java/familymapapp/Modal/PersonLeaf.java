package familymapapp.Modal;

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
    //searches all of event fields for certain value
    public boolean containsValue(String constraint){
        //if(event.getCity.contains(constraint)) return true;
    }
}