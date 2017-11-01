// /**
//  * API's with DBConnection and returns/manages models
//  */
public class EventAccess extends DataAccess{

//     /** Reference to Singleton DB*/
    final private String relation = "EventAccess";

    public EventAccess(DBConnection dbConnection){
        super(dbConnection);
    }

    /** 
    * creates Event in db modeled after parameters
    * @param Event event
    * @return Event
    */
    //int id, int lattitude, int longitude, String country, String city, int eventTypeId, Year year
    public Event create(String event){
        String attributes = MessageFormat.format(
            " {0} {1} {2} {3} {4} {5}",
            "lattitude",
            "longitude",
            "country",
            "city",
            "typeId",
            "year",
        );
        String values = MessageFormat.format(
            "''{0}'' ''{1}'' ''{2}'' ''{3}'' ''{4}'' ''{5}''",
            event.getLattitude(), 
            event.getLongitude(), 
            event.getCountry(), 
            event.getCity(), 
            event.getTypeId(), 
            event.getYear(), 
        );

        int id = super.create(this.relation, attributes, values);
        return new Event(id, eventType);
    }

    /** 
    * gets events with specified value
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return Array of Events
    */
    public Event get(String key, String delimeter, String desiredValue){
        ResultSet result = super.get(this.relation, key, delimeter, desiredValue);
        Event[] events = null;
        try{
            while(result.next()){
                int id = result.getInt("id");
                String lattitude = result.getString("lattitude");
                String longitude = result.getString("longitude");
                String country = result.getString("country");
                String city = result.getString("city");
                String typeId = result.getString("typeId");
                String year = result.getString("year");
                events[] = new Event(id, lattitude, longitude, country, city, typeId);
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
            "{0} = ''{1}'' {2} = ''{3}'' {4} = ''{5}'' {6} = ''{7}'' {8} = ''{9}'' {10} = ''{11}''",
            "lattitude", event.getLattitude,
            "longitude", event.getLongitude,
            "country", event.getCountry,
            "city", event.getCity,
            "typeId", event.getTypeId,
            "year", event.getYear
        );
        return super.update(this.relation, changes, "id", "=", Integer.toString(eventType.getId()));
    }

    /** 
    * delete specific EventType
    * @param eventType eventType to be deleted
    * @return boolean whether deletion was successfull
    */
    public boolean delete(Event event){
        return super.delete(this.relation, "id", "=", Integer.toString(event.getId()));
    }

}