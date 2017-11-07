package com.familymap;

import static com.familymap.Util.*;
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
    final private String relation = "event";

    public EventAccess(DBConnection dbConnection){
        super(dbConnection);
    }

    /** 
    * creates Event in db modeled after parameters
    * @param Event event
    * @return Event
    */
    //int id, int latitude, int longitude, String country, String city, int eventTypeId, Year year
    public Event create(String id, String latitude, String longitude, String country, String city, String type, String year, String personId, String descendant){
        country = country.replace("'", "");
        city = city.replace("'", "");
        String attributes = MessageFormat.format(
            "{0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}",
            "id",
            "latitude",
            "longitude",
            "country",
            "city",
            "type",
            "year",
            "person_id",
            "descendant"
        );
        String values = MessageFormat.format(
            "''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'', ''{6}'', ''{7}'', {8}",
            id,
            latitude, 
            longitude, 
            country, 
            city, 
            type, 
            year,
            personId,
            descendant != null ? "'" + descendant + "'" : "NULL"
        );

        super.rawCreate(this.relation, attributes, values);
        return new Event(id, latitude, longitude, country, city, type, year, personId, descendant);
    }

    /** 
    * gets events with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return ArrayList of Events
    */
    public ArrayList<Event> get(String key, String delimeter, String desiredValue){
        desiredValue = MessageFormat.format(
            "''{0}''",
            desiredValue
        );
        return this.preformGet(key, delimeter, desiredValue);
    }
    public ArrayList<Event> get(String key, String delimeter, ArrayList<String> desiredValues){
        return this.preformGet(key, delimeter, Util.arrayListToString(desiredValues));
    }

    public ArrayList<Event> preformGet(String key, String  delimeter, String desiredValue){
        ArrayList<Event> events = new ArrayList<Event>();
        try{
            ResultSet result = super.rawGet(this.relation, key, delimeter, desiredValue);
            while(result.next()){
                String id = result.getString("id");
                String latitude = result.getString("latitude");
                String longitude = result.getString("longitude");
                String country = result.getString("country");
                String city = result.getString("city");
                String type = result.getString("type");
                String year = result.getString("year");
                String personId = result.getString("person_id");
                String descendant = result.getString("descendant");
                events.add(new Event(id, latitude, longitude, country, city, type, year, personId, descendant));
            }
             result.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return events;
    }

    /** 
    * update specific EventType in db
    * @param event event with new parameters
    * @return EventType
    */
    // public boolean update(Event event){
    //     String changes = MessageFormat.format(
    //         "{0} = ''{1}'', {2} = ''{3}'', {4} = ''{5}'', {6} = ''{7}'', {8} = ''{9}'', {10} = ''{11}''",
    //         "latitude", event.getlatitude(),
    //         "longitude", event.getLongitude(),
    //         "country", event.getCountry(),
    //         "city", event.getCity(),
    //         "type", event.getEventType(),
    //         "year", event.getYear()
    //     );   
    //     return super.rawUpdate(this.relation, changes, "id", "=", event.getId());
    // }

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

}
