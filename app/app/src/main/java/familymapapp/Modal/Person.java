package familymapapp.Modal;

import java.util.HashMap;

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

    /** extra info about person for handling state */
    public HashMap<String, String> metaData;

    public Person(String id, String firstName, String lastName, String gender, String fatherId, String motherId, String spouseId, String descendant){
        this.id = id;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.spouseId = spouseId;
        metaData = new HashMap<String, String>();
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
        return "******";
                //metaData.get("type");
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
}