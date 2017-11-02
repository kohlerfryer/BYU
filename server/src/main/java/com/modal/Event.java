 package com.familymap;
 
 /**
 * represents a single Event tuple
 */
 public class Event{
     /** tuples unique identifier in relation */
     private String id;

     /** lattitude of event location */
     private String lattitude;

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

     public Event(String id, String lattitude, String longitude, String country, String city, String type, String year){
         this.id = id;
         this.lattitude = lattitude;
         this.longitude = longitude;
         this.country = country;
         this.city = city;
         this.type = type;
         this.year = year;
     }

     public String getId(){
	 return this.id;
     }
     public String getLattitude(){
	 return this.lattitude;
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

     public void setLattitude(String lattitude){
	 this.lattitude = lattitude;
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
  
 }
