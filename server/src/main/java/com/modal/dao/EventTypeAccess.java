package com.familymap;

import com.familymap.DBConnection;
import com.familymap.EventType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

/**
* API's with DBConnection and returns/manages models
*/
public class EventTypeAccess extends DataAccess{

    final private String relation = "EventType";

    public EventTypeAccess(DBConnection dbConnection){
        super(dbConnection);
    }

    /** 
    * creates EventType in db modeled after parameters
    * @param eventType type of event
    * @return EventType
    */
    //change all data types to string :)*******************************************************************************
    public EventType create(String eventType){
        String id = super.create(this.relation, "type", "'" + eventType + "'");
        return new EventType(id, eventType);
    }

    /** 
    * gets events with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return EventType
    */
    public EventType get(String key, String delimeter, String desiredValue){
        ResultSet result = super.get(this.relation, key, delimeter, desiredValue);
        EventType eventType = null;
        try{
            result.next();
            int id = result.getInt("id");
            String type = result.getString("type");
            eventType = new EventType(id, type);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return eventType;
    }

    /** 
    * update specific EventType in db
    * @param event event with new parameters
    * @return EventType
    */
    public boolean update(EventType eventType){
        String changes = MessageFormat.format(
            " {0} = ''{1}'' ",
            "type", 
            eventType.getType()
        );
        return super.update(this.relation, changes, "id", "=", Integer.toString(eventType.getId()));
    }

    /** 
    * delete specific EventType
    * @param eventType eventType to be deleted
    * @return boolean whether deletion was successfull
    */
    public boolean delete(EventType eventType){
        return super.delete(this.relation, "id", "=", Integer.toString(eventType.getId()));
    }

}
