package familymapapp.Modal;

import com.google.android.gms.maps.model.LatLng;

import fryer.kohler.familymapapp.DetailsRowViewHolder;
import fryer.kohler.familymapapp.R;

/**
 * represents a single Event tuple
 */
 public class Event implements DetailsRowDataObject{
     /** tuples unique identifier in relation */
     private String id;

     /** latitude of event location */
     private String latitude;

     /** longitude of event location */
     private String longitude;

     /** country of event */
     private String country;

     /** city of event */
     private String city;

     /** refers to eventType a relation tuple*/
     private String type;

     /** year of event */
     private String year;

     /** person attatched to event */
     private String personId;

     /** user attatched to event */
     private String descendant;
     
//{"id":"03VL7DFJXKB56KIC5W","":"52.5167","longitude":"13.3833","country":"Germany","city":"Berlin","type":"baptism","year":"1832","personId":"1832","descendant":"aa"}
     public Event(String id, String latitude, String longitude, String country, String city, String type, String year, String personId, String descendant){
         this.id = id;
         this.latitude = latitude;
         this.longitude = longitude;
         this.country = country;
         this.city = city;
         this.type = type;
         this.year = year;
         this.personId = personId;
         this.descendant = descendant;
     }

    @Override
    public String getFirstRow() {

         return getEventType() + ":" + getCity() + ", " + getCountry() + "(" + getYear() + ")" ;
    }

    @Override
    public String getSecondRow() {
         return DataTree.getPersons().get(getPersonId()).getFirstName() + " " + DataTree.getPersons().get(getPersonId()).getLastName();
    }

    @Override
    public int getIcon() {
         return R.drawable.common_google_signin_btn_icon_dark_normal;
    }

    public String getId(){
	 return this.id;
     }
     public String getLatitude(){
	 return this.latitude;
     }
     public String getLongitude(){
	 return this.longitude;
     }
     public String getCountry(){
	 return this.country;
     }
     public String getCity(){
	 return this.city;
     }
     public String getEventType(){
	 return this.type;
     }
     public String getYear(){
	 return this.year;
     }
     public String getPersonId(){
	 return this.personId;
     }
     public String getDescendant(){
	 return this.descendant;
     }
     public LatLng convertCoordinatesToLtLng(){
         Double latitude = Double.valueOf(getLatitude());
         Double longitude = Double.valueOf(getLongitude());
         return new LatLng(latitude,longitude);
     }


     public void setlatitude(String latitude){
	 this.latitude = latitude;
     }
     public void setLongitude(String longitude){
	 this.longitude = longitude;
     }
     public void setCountry(String country){
	 this.country = country;
     }
     public void setCity(String city){
	 this.city = city;
     }
     public void setType(String type){
	 this.type = type;
     }
     public void setYear(String year){
	 this.year = year;
     }
     public void setPersonId(String personId){
	 this.personId = personId;
     }
     public void setDescendant(String descendant){
	 this.descendant = descendant;
     }
  
 }
