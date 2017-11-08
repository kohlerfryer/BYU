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
public class UserAccess extends DataAccess{

    /** Reference to Singleton DB*/
    final private String relation = "user";

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
    public User create(String username, String email, String firstName, String lastName, String gender, String password, String personId){
        String attributes = MessageFormat.format(
            "{0}, {1}, {2}, {3}, {4}, {5}, {6}",
            "username",
            "email",
            "first_name",
            "last_name",
            "gender",
            "password",
            "person_id"
        );
        String values = MessageFormat.format(
            "''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'', ''{6}''",
            username, 
            email,
            firstName,
            lastName,
            gender,
            password,
            personId
        );

        super.rawCreate(this.relation, attributes, values);
        return new User(username, email, firstName, lastName, gender, password, personId);
    }

    /** 
    * gets user event with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return User
    */
    public ArrayList<User> get(String key, String delimeter, String desiredValue){
        desiredValue = MessageFormat.format(
            "''{0}''",
            desiredValue
        );
        ArrayList<User> users = new ArrayList<User>();
        try{
            ResultSet result = super.rawGet(this.relation, key, delimeter, desiredValue);
            while(result.next()){
                String username = result.getString("username");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String gender = result.getString("gender");
                String personId = result.getString("person_id");
                users.add(new User(username, email, firstName, lastName, gender, password, personId));
            }
            result.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return users;
    }


}
