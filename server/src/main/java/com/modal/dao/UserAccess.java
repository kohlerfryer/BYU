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
    public User create(String username, String email, String personId, String password){
        String attributes = MessageFormat.format(
            "{0}, {1}, {2}, {3}",
            "username",
            "email",
            "person_id",
            "password"
        );
        String values = MessageFormat.format(
            "''{0}'', ''{1}'', ''{2}'', ''{3}''",
            username, 
            email, 
            personId, 
            Util.getHash(password)
        );

        String id = super.rawCreate(this.relation, attributes, values);
        return new User(id, username, email, personId);
    }

    /** 
    * gets user event with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return User
    */
    public ArrayList<User> get(String key, String delimeter, String desiredValue){
        ResultSet result = super.rawGet(this.relation, key, delimeter, desiredValue);
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
