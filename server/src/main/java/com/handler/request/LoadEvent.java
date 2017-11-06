public class LoadEvent{
    private String eventID;
    private String personID;
    private String latitude;
    private String latitude;
    private String longitude;
    private String country;
    private String city;
    private String eventType;
    private String year;

    LoadEvent(String eventID, String personID, String latitude, String longitude, String country, String city, String eventType, String year){
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;        
    }

}