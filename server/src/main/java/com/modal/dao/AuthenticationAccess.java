package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Authentication;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * API's with DBConnection and returns/manages models
 */
public class AuthenticationAccess extends DataAccess{

    /** Reference to Singleton DB*/
    final private String relation = "Authentication";

    public AuthenticationAccess(DBConnection db){
        super(db);
    }

    /** 
    * creates authentication in db modeled after parameters
    * @param token unique session token
    * @param userId the user who owns token
    * @param timestamp creation date of token
    * @return Authentication
    */
    public Authentication create(String userId){
        //int id, String token, int userId, String timestamp
        String token = Util.generateRandomString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String attributes = MessageFormat.format(
            "{0}, {1}, {2}",
            "token",    
            "user_id",
            "time_stamp"
        );
        String values = MessageFormat.format(
            "''{0}'', ''{1}'', ''{2}''",
            token, 
            userId,
            timestamp.toString()
        );

        String id = super.create(this.relation, attributes, values);
        return new Authentication(id, token, userId, timestamp.toString());
    }

    /** 
    * gets authentication with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return Authentication
    */
    public ArrayList<Authentication> get(String key, String delimeter, String desiredValue){
        ResultSet result = super.get(this.relation, key, delimeter, desiredValue);
        ArrayList<Authentication> authentications = new ArrayList<Authentication>();
        try{
            while(result.next()){
                String id = result.getString("id");
                String token = result.getString("token");
                String userId = result.getString("user_id");
                String timestamp = result.getString("time_stamp");
                authentications.add(new Authentication(id, token, userId, timestamp));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return authentications;
    }

}