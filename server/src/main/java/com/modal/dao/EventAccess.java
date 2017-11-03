package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Event;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;


/**
 * API's with DBConnection and returns/manages models
 */
public class EventAccess extends DataAccess{

    /** Reference to Singleton DB*/
    final private String relation = "Event";

    public EventAccess(DBConnection dbConnection){
        super(dbConnection);
    }

    /** 
    * creates Event in db modeled after parameters
    * @param Event event
    * @return Event
    */
    //int id, int lattitude, int longitude, String country, String city, int eventTypeId, Year year
    public Event create(String lattitude, String longitude, String country, String city, String type, String year){
        String attributes = MessageFormat.format(
            "{0}, {1}, {2}, {3}, {4}, {5}",
            "lattitude",
            "longitude",
            "country",
            "city",
            "type",
            "year"
        );
        String values = MessageFormat.format(
            "''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}''",
            lattitude, 
            longitude, 
            country, 
            city, 
            type, 
            year
        );

        String id = super.rawCreate(this.relation, attributes, values);
        return new Event(id, lattitude, longitude, country, city, type, year);
    }

    /** 
    * gets events with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return ArrayList of Events
    */
    public ArrayList<Event> get(String key, String delimeter, String desiredValue){
        ResultSet result = super.rawGet(this.relation, key, delimeter, desiredValue);
        ArrayList<Event> events = new ArrayList<Event>();
        try{
            while(result.next()){
                String id = result.getString("id");
                String lattitude = result.getString("lattitude");
                String longitude = result.getString("longitude");
                String country = result.getString("country");
                String city = result.getString("city");
                String type = result.getString("type");
                String year = result.getString("year");
                events.add(new Event(id, lattitude, longitude, country, city, type, year));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return events;
    }

    /** 
    * update specific EventType in db
    * @param event event with new parameters
    * @return EventType
    */
    public boolean update(Event event){
        String changes = MessageFormat.format(
            "{0} = ''{1}'', {2} = ''{3}'', {4} = ''{5}'', {6} = ''{7}'', {8} = ''{9}'', {10} = ''{11}''",
            "lattitude", event.getLattitude(),
            "longitude", event.getLongitude(),
            "country", event.getCountry(),
            "city", event.getCity(),
            "type", event.getEventType(),
            "year", event.getYear()
        );
        return super.rawUpdate(this.relation, changes, "id", "=", event.getId());
    }

    /** 
    * delete specific EventType
    * @param eventType eventType to be deleted
    * @return boolean whether deletion was successfull
    */
    public boolean delete(Event event){
        return super.rawDelete(this.relation, "id", "=", event.getId());
    }

}
