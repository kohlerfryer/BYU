package com.familymap;

import com.familymap.LoadUser;
import com.familymap.LoadPerson;
import com.familymap.LoadEvent;
import java.util.ArrayList;

public class LoadRequestBody{
    private LoadUser[] users;
    private LoadPerson[] persons;
    private LoadEvent[] events;
    private String success;

    LoadRequestBody(LoadUser[] users, LoadPerson[]  persons, LoadEvent[] events){
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public LoadUser[] getLoadUsers(){
        return this.users;
    }
    public LoadPerson[] getLoadPersons(){
        return this.persons;
    }
    public LoadEvent[] getLoadEvents(){
        return this.events;
    }

    public void validate(UserAccess userAccess) throws InvalidRequestException{
        boolean validFields = true;
            for (LoadPerson person : persons) {
                person.validate(userAccess);
            }
            for (LoadUser user : users) {
                user.validate(userAccess);
            }
            for (LoadEvent event : events) {
                event.validate(userAccess);
            }
    }


}

// {
// 	"users":[
//                 {
//                     "userName": "susan", 
//                     "password": "mysecret", 
//                     "email": "susan@gmail.com",
//                     "firstName": "Susan", 
//                     "lastName": "Ellis",
//                     "gender": "f" ,
//                     "personID": "asdfasdf"
//                 }
// 	],
// 	"persons":[
//                 {
//                     "descendant": "susan", 
//                     "personID": "SusanEllis", 
//                     "firstName": "Stuart",
//                     "lastName": "Klocke", 
//                     "gender": "m", 
//                     "father": "7255e93e",
//                     "mother": "f42126c8" ,
//                     "spouse":"f42126c8" 
//                 },
//                 {
//                     "descendant": "susan", 
//                     "personID": "7255e93e", 
//                     "firstName": "Stuart",
//                     "lastName": "Klocke", 
//                     "gender": "m", 
//                     "father": "7255e93e",
//                     "mother": "f42126c8" ,
//                     "spouse":"f42126c8" 
//                 }
// 	],
// 	"events":[
//                 {
//                     "descendant": "susan",
//                     "eventID": "251837d7", 
//                     "personID": "7255e93e", 
//                     "latitude": "65.6833", 
//                     "longitude": "-17.9", 
//                     "country": "Iceland", 
//                     "city": "Akureyri", 
//                     "eventType": "birth",
//                     "year": "1912"
//                 },
//                 {
//                     "descendant": "susan",
//                     "eventID": "3333e4343", 
//                     "personID": "7255e93e", 
//                     "latitude": "65.6833", 
//                     "longitude": "-17.9", 
//                     "country": "Iceland", 
//                     "city": "Akureyri", 
//                     "eventType": "birth",
//                     "year": "1912"
//                 }
// 	]
// }










// {
// “users”: [ /* Array of User objects */ ],
// “persons”: [ /* Array of Person objects */ ],
// “events”: [ /* Array of Event objects */ ]
// }




// users
// {
// "userName": "susan", // Non-empty string
// "password": "mysecret", // Non-empty string
// "email": "susan@gmail.com", // Non-empty string
// "firstName": "Susan", // Non-empty string
// "lastName": "Ellis", // Non-empty string
// "gender": "f" // “f” or “m”
// "personid": "asdfasdf"
// }

// persons
// {
// "descendant": "susan", // Name of user account this person belongs to
// "personID": "7255e93e", // Person’s unique ID
// "firstName": "Stuart", // Person’s first name
// "lastName": "Klocke", // Person’s last name
// "gender": "m", // Person’s gender (“m” or “f”)
// “father”: “7255e93e” // ID of person’s father [OPTIONAL,​ ​can​ ​be​ ​missing]
// “mother”: “f42126c8” // ID of person’s mother [OPTIONAL,​ ​can​ ​be​ ​missing]
// "spouse":"f42126c8" // ID of person’s spouse [OPTIONAL,​ ​can​ ​be​ ​missing]
// }

// events
// {
// "descendant": "susan" // Name of user account this event belongs to (non-empty
// // string)
// "eventID": "251837d7", // Event’s unique ID (non-empty string)
// "personID": "7255e93e", // ID of the person this event belongs to (non-empty string)
// "latitude": 65.6833, // Latitude of the event’s location (number)
// "longitude": -17.9, // Longitude of the event’s location (number)
// "country": "Iceland", // Name of country where event occurred (non-empty
// // string)
// "city": "Akureyri", // Name of city where event occurred (non-empty string)
// "eventType": "birth", // Type of event (“birth”, “baptism”, etc.) (non-empty string)
// "year": 1912, // Year the event occurred (integer)
// }