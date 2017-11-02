package com.familymap;

import com.familymap.DBConnection;
import com.familymap.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;


/**
 * API's with DBConnection and returns/manages models
 */
public class PersonAccess extends DataAccess{
    
    /** Reference to Singleton DB*/
    private DataHandler db;

    public PersonAccess(DataHandler db){}

    /** 
    * gets person with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return String matching eventType
    */
    public Person getPerson(String key, string delimeter, String desiredValue){}

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
    public Person create(Name firstName, Name lastName, char gender, int fatherId, int motherId, int spouseId){
        String attributes = MessageFormat.format(
            "{0}, {1}, {2}, {3}, {4}, {5}",
            "first_name",
            "last_name",
            "gender",
            "father_id",
            "mother_id",
            "spouse_id"
        );
        String values = MessageFormat.format(
            "''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}''",
            firstName, 
            lastName, 
            gender, 
            fatherId, 
            motherId, 
            spouseId
        );
//     public Person(int id, Name firstName, Name lastName, char gender, int fatherId, int motherId, int spouseId){}

        String id = super.create(this.relation, attributes, values);
        return new User(id, firstName, lastName, gender, fatherId, motherId, spouseId);
    }
    /** 
    * update specific user in db
    * @param user user with new parameters
    * @return EventType
    */
    public Person updatePerson(Person user){}
    
    // /** 
    // * generates random data for a user up
    // * to desired generation length
    // * @param generationSize amount of generations of data to make
    // * @return void
    // */
    // public void generateFamilyData(int generationSize){}

}



/**
 * API's with DBConnection and returns/manages models
 */
public class UserAccess extends DataAccess{

    /** Reference to Singleton DB*/
    final private String relation = "User";

    public UserAccess(DBConnection dbConnection){
        super(dbConnection);
    }

    /** 
    * creates user event in db modeled after parameters
    * @param username username of user
    * @param email email of user
    * @param personId email of user
    * @return User
    */
//  String id, String username, String email, String personId


    /** 
    * gets user event with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return User
    */
    public ArrayList<User> get(String key, String delimeter, String desiredValue){
        ResultSet result = super.get(this.relation, key, delimeter, desiredValue);
        ArrayList<User> users = new ArrayList<User>();
        try{
            while(result.next()){
                String id = result.getString("id");
                String username = result.getString("username");
                String email = result.getString("email");
                String personId = result.getString("person_id");
                users.add(new User(id, username, email, personId));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }


}
