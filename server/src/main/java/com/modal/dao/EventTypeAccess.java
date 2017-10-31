// package com.familymap;

// import com.familymap.DBConnection;
// import com.familymap.EventType;

// /**
//  * API's with DBConnection and returns/manages models
//  */
// public class EventTypeAccess extends DataAccess{

//     final private String relation = "EventType";

//     public EventTypeAccess(DBConnection dbConnection){
//         super(dbConnection);
//     }

//     /** 
//     * gets events with specified value
//     * @param key value identifier of tuple of first Relation on which to project
//     * @param delimeter delimiter used with key
//     * @param desiredValue dilimeted value
//     * @return EventType
//     */
//     public EventType get(String key, String delimeter, String desiredValue){
//         ResultSet result = super.get(this.relation, key, delimeter, desiredValue);
//         int id = result.getInt("id");
//         String type = result.getString("type"); 
//         return new EventType(id, type);
//     }

//     /** 
//     * creates EventType in db modeled after parameters
//     * @param eventType type of event
//     * @return EventType
//     */
//     public EventType create(String eventType){

//     }

//     /** 
//     * update specific EventType in db
//     * @param event event with new parameters
//     * @return EventType
//     */
//     public EventType update(EventType eventType){

//     }

// }
