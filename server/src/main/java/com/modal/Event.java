 package com.familymap;
 
 /**
 * represents a single Event tuple
 */
 public class Event{
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

     public Event(String id, String latitude, String longitude, String country, String city, String type, String year, String personId){
         this.id = id;
         this.latitude = latitude;
         this.longitude = longitude;
         this.country = country;
         this.city = city;
         this.type = type;
         this.year = year;
     }

     public String getId(){
	 return this.id;
     }
     public String getlatitude(){
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
  
 }
