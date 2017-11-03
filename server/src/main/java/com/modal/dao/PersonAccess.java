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
    final private String relation = "Person";

    public PersonAccess(DBConnection dbConnection){
        super(dbConnection);
    }

    // /** 
    // * creates person in db modeled after parameters
    // * @param firstName firstName of person
    // * @param lastName lastName of person
    // * @param gender gender of person
    // * @param fatherId treferrs to father tuple of this person 
    // * @param motherId referrs to mohter tuple of this person 
    // * @param spouseId referrs to spouse tuple of this person 
    // * @return Person
    // */
    // public Person create(String firstName, String lastName, String gender, String fatherId, String motherId, String spouseId){
    //     String attributes = MessageFormat.format(
    //         "{0}, {1}, {2}, {3}, {4}, {5}",
    //         "first_name",
    //         "last_name",
    //         "gender",
    //         "father_id",
    //         "mother_id",
    //         "spouse_id"
    //     );
    //     String values = MessageFormat.format(
    //         "''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}''",
    //         firstName, 
    //         lastName, 
    //         gender, 
    //         fatherId, 
    //         motherId, 
    //         spouseId
    //     );

    //     String id = super.create(this.relation, attributes, values);
    //     return new Person(id, firstName, lastName, gender, fatherId, motherId, spouseId);
    // }

    /** 
    * creates person in db modeled after parameters
    * @param firstName firstName of person
    * @param lastName lastName of person
    * @param gender gender of person
    * @return Person
    */
    public Person create(String firstName, String lastName, String gender){
        String attributes = MessageFormat.format(
            "{0}, {1}, {2}",
            "first_name",
            "last_name",
            "gender"
        );
        String values = MessageFormat.format(
            "''{0}'', ''{1}'', ''{2}''",
            firstName, 
            lastName, 
            gender
        );

        String id = super.create(this.relation, attributes, values);
        return new Person(id, firstName, lastName, gender);
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
        ResultSet result = super.get(this.relation, key, delimeter, desiredValue);
        ArrayList<Person> Person = new ArrayList<Person>();
        try{
            while(result.next()){
                String id = result.getString("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String gender = result.getString("gender");
                String fatherId = result.getString("father_id");
                String motherId = result.getString("mother_id");
                String spouseId = result.getString("spouse_id");
                Person.add(new Person(id, firstName, lastName, gender, fatherId, motherId, spouseId));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Person;
    }

    /** 
    * update specific user in db
    * @param user user with new parameters
    * @return EventType
    */
    // public Person updatePerson(Person user){}
    
    // /** 
    // * generates random data for a user up
    // * to desired generation length
    // * @param generationSize amount of generations of data to make
    // * @return void
    // */
    // public void generateFamilyData(int generationSize){}

}

