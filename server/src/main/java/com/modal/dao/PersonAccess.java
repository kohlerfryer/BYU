package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * API's with DBConnection and returns/manages models
 */
public class PersonAccess extends DataAccess{
    
    /** Reference to Singleton DB*/
    final private String relation = "person";

    public PersonAccess(DBConnection dbConnection){
        super(dbConnection);
    }

    // /** 
    // * creates person in db modeled after parameters
    // * @param firstName firstName of person
    // * @param lastName lastName of person
    // * @param gender gender of person
    // * @return Person
    // */
    // public Person create(String firstName, String lastName, String gender){
    //     String attributes = MessageFormat.format(
    //         "{0}, {1}, {2}",
    //         "first_name",
    //         "last_name",
    //         "gender"
    //     );
    //     String values = MessageFormat.format(
    //         "''{0}'', ''{1}'', ''{2}''",
    //         firstName, 
    //         lastName, 
    //         gender
    //     );

    //     String id = super.rawCreate(this.relation, attributes, values);
    //     return new Person(id, firstName, lastName, gender);
    // }

    /** 
    * creates person in db modeled after parameters
    * @param firstName firstName of person
    * @param lastName lastName of person
    * @param gender gender of person
    * @param fatherId treferrs to father tuple of this person 
    * @param motherId referrs to mohter tuple of this person 
    * @param spouseId referrs to spouse tuple of this person 
    * @return Person
    */
    public Person create(String id, String firstName, String lastName, String gender, String fatherId, String motherId, String spouseId, String descendant){
        String attributes = MessageFormat.format(
            "{0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}",
            "id",
            "first_name",
            "last_name",
            "gender",
            "father_id",
            "mother_id",
            "spouse_id",
            "descendant"
        );
        String values = MessageFormat.format(
            "''{0}'', ''{1}'', ''{2}'', ''{3}'', {4}, {5}, {6}, {7}",
            id,
            firstName, 
            lastName, 
            gender, 
            fatherId != null ? "'" + fatherId + "'" : "NULL", 
            motherId != null ? "'" + motherId + "'" : "NULL", 
            spouseId != null ? "'" + spouseId + "'" : "NULL",
            descendant != null ? "'" + descendant + "'" : "NULL"
        );
        //System.out.println("**********************" + values);
        super.rawCreate(this.relation, attributes, values);
        return new Person(id, firstName, lastName, gender, fatherId, motherId, spouseId, descendant);
    }


    /** 
    * gets person with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return String matching eventType
    */
//     public Person(int id, Name firstName, Name lastName, char gender, int fatherId, int motherId, int spouseId){}

    public ArrayList<Person> get(String key, String delimeter, String desiredValue){
        desiredValue = MessageFormat.format(
            "''{0}''",
            desiredValue
        );
        ArrayList<Person> Person = new ArrayList<Person>();
        try{
            ResultSet result = super.rawGet(this.relation, key, delimeter, desiredValue);
            while(result.next()){
                String id = result.getString("id");
                String descendant = result.getString("descendant");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String gender = result.getString("gender");
                String fatherId = result.getString("father_id");
                String motherId = result.getString("mother_id");
                String spouseId = result.getString("spouse_id");
                Person.add(new Person(id, firstName, lastName, gender, fatherId, motherId, spouseId, descendant));
            }
             result.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return Person;
    }

    /** 
    * update specific user in db
    * @param user user with new parameters
    * @return EventType
    */
    public boolean update(Person person){
        String changes = MessageFormat.format(
            "{0} = ''{1}'', {2} = ''{3}'', {4} = ''{5}'', {6} = {7}, {8} = {9}, {10} = {11}",
            "first_name", person.getFirstName(),
            "last_name", person.getLastName(),
            "gender", person.getGender(),
            "father_id", person.getFatherId() != null ? "'" + person.getFatherId() + "'" : "NULL",
            "mother_id", person.getMotherId() != null ? "'" + person.getMotherId() + "'" : "NULL",
            "spouse_id", person.getSpouseId() != null ? "'" + person.getSpouseId() + "'" : "NULL"
        );
        //System.out.println("**********************" + changes);
        String desiredValue = MessageFormat.format(
            "''{0}''",
            person.getId()
        );
        return super.rawUpdate(this.relation, changes, "id", "=", desiredValue);
    }

    /** 
    * delete specific EventType
    * @param eventType eventType to be deleted
    * @return boolean whether deletion was successfull
    */
    public int delete(String key, String delimeter, String desiredValue){
        desiredValue = MessageFormat.format(
            "''{0}''",
            desiredValue
        );
        return super.rawDelete(this.relation, key, delimeter, desiredValue);
    }
    public int delete(String key, String delimeter,  ArrayList<String> desiredValues){
        return super.rawDelete(this.relation, key, delimeter, Util.arrayListToString(desiredValues));
    }










    // /** 
    // * generates random data for a user up
    // * to desired generation length
    // * @param generationSize amount of generations of data to make
    // * @return void
    // */
    // public void generateFamilyData(int generationSize){}



    // public ArrayList<String> getAncestorIds(String personId){
    //     ArrayList<String> ids = new ArrayList<String>();
    //     ids.add(personId);
    //     ids.addAll(getParentsIds(personId));
    //     return ids;
    // }
//getParentsIds
    public ArrayList<String> getAncestorIds(String personId){
        ArrayList<String> ids = new ArrayList<String>();
        if (personId != null){
            ArrayList<Person> personList = this.get("id", "=", personId);
            Person person = personList.get(0);
            if(person.getFatherId() != null)
                ids.add(person.getFatherId());
            if(person.getMotherId() != null)
                ids.add(person.getMotherId());
            ids.addAll(getAncestorIds(person.getFatherId()));
            ids.addAll(getAncestorIds(person.getMotherId()));
        }
        return ids;
    }


}

