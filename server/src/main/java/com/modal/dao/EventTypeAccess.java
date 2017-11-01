package com.familymap;

import com.familymap.DBConnection;
import com.familymap.EventType;
import java.text.MessageFormat;

// /**
//  * API's with DBConnection and returns/manages models
//  */
public class EventTypeAccess extends DataAccess{

    final private String relation = "EventType";

    public EventTypeAccess(DBConnection dbConnection){
        super(dbConnection);
    }

//     /** 
//     * gets events with specified value
//     * @param key value identifier of tuple of first Relation on which to project
//     * @param delimeter delimiter used with key
//     * @param desiredValue dilimeted value
//     * @return EventType
//     */
    public EventType get(String key, String delimeter, String desiredValue){
        ResultSet result = super.get(this.relation, key, delimeter, desiredValue);
        result.next();
        int id = result.getInt("id");
        String type = result.getString("type"); 
        return new EventType(id, type);
    }

//     /** 
//     * creates EventType in db modeled after parameters
//     * @param eventType type of event
//     * @return EventType
//     */
    public EventType create(String eventType){
        int id = super.create(this.relation, "type", "'" + eventType + "''");
        return new EventType(id, eventType);
    }

//     /** 
//     * update specific EventType in db
//     * @param event event with new parameters
//     * @return EventType
//     */
    public EventType update(EventType eventType){
        String changes = MessageFormat.format(
            "
            {0} = {1}
            {2} = {3}
            ",
            r
        );
        changes += '" +eventType.+  "';
        super.update(this.relation, String changes, String key, String delimeter, String value){
        return null;
    }

}
