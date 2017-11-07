package com.familymap;

import com.familymap.DBConnection;
import static com.familymap.DBSingleton.*;
import static com.familymap.Util.*;
import com.familymap.Person;
import com.familymap.PersonAccess;
import com.familymap.EventAccess;
import com.familymap.Event;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;


public class DataGenerator{

    private PersonAccess personAccess;
    private EventAccess eventAccess;
    private Random random;

    public DataGenerator(){
	    DBConnection connection = DBSingleton.getInstance();
        this.personAccess = new PersonAccess(connection);
        this.eventAccess = new EventAccess(connection);
        this.random = new Random();
    }
















    public void generatePersonData(Person person, int generations, int year){
        year-=40;
        ArrayList<Person> result = this.personAccess.get("id", "=", person.getId());
        Person currentPerson = result.get(0);
        this.setPersonData(currentPerson, year);
        Person mother = generateParentData(this.generateLastName(), "F", generations -1, year);
        Person father = generateParentData(person.getLastName(), "M", generations -1, year);
        father.setSpouseId(mother.getId());
        mother.setSpouseId(father.getId());
        currentPerson.setMotherId(mother.getId());
        currentPerson.setFatherId(father.getId());
        this.personAccess.update(currentPerson);
        this.personAccess.update(father);
        this.personAccess.update(mother);
    }

    private Person generateParentData(String lastName, String gender, int generations, int year){
        year -=40;
        Person currentPerson = this.personAccess.create(Util.generateRandomString(), this.generateFirstName(gender), lastName, gender, null, null, null, null);
        this.setAncestorData(currentPerson, year);
        if(generations >  0){
            Person mother = generateParentData(this.generateLastName(), "F", generations -1, year);
            Person father = generateParentData(lastName, "M", generations -1, year);
            father.setSpouseId(mother.getId());
            mother.setSpouseId(father.getId());
            currentPerson.setMotherId(mother.getId());
            currentPerson.setFatherId(father.getId());
            //this.setMarriageData(father, mother);
            this.personAccess.update(father);
            this.personAccess.update(mother);
            this.personAccess.update(currentPerson);
            this.generateMarriageEvent(year, mother.getId(), father.getId());
        }
        return currentPerson;
    }



















    

    private void setAncestorData(Person person, int year){
        String personId = person.getId();
        Event birth = this.generateBirthEvent(year, personId);
        int birthDate = Integer.valueOf(birth.getYear());
        this.generateDeathEvent(birthDate, personId);
        if(Math.random() < 0.5){
            this.generateBaptismEvent(birthDate, personId);  
        }

    }

    public void setPersonData(Person person, int year){
        this.generateBirthEvent(year, person.getId()); 
    }














    private Event generateBirthEvent(int year, String personId){
        String birthYear = Integer.toString(this.generateBirthDate(year));
        String type = "birth";
        JsonObject location = this.generateLocation();
        return this.eventAccess.create(
            Util.generateRandomString(),
            location.get("latitude").getAsString(), 
            location.get("longitude").getAsString(), 
            location.get("country").getAsString(), 
            location.get("city").getAsString(), 
            type,
            birthYear,
            personId,
            null
        );
    }

    private Event generateDeathEvent(int year, String personId){
        String deathYear = Integer.toString(this.generateDeathDate(year));
        String type = "death";
        JsonObject location = this.generateLocation();
        return this.eventAccess.create(
            Util.generateRandomString(),
            location.get("latitude").getAsString(), 
            location.get("longitude").getAsString(), 
            location.get("country").getAsString(), 
            location.get("city").getAsString(), 
            type,
            deathYear,
            personId,
            null
        );
    }

    private Event generateBaptismEvent(int year, String personId){
        String baptismYear = Integer.toString(this.generateBatismDate(year));
        String type = "baptism";
        JsonObject location = this.generateLocation();
        return this.eventAccess.create(
            Util.generateRandomString(),
            location.get("latitude").getAsString(), 
            location.get("longitude").getAsString(), 
            location.get("country").getAsString(), 
            location.get("city").getAsString(), 
            type,
            baptismYear,
            personId,
            null
        );
    }

    private Event generateMarriageEvent(int year, String womanPersonId, String malePersonId){
        String marriageYear = Integer.toString(this.generateBirthDate(year));
        String type = "marriage";
        JsonObject location = this.generateLocation();
        this.eventAccess.create(
            Util.generateRandomString(),
            location.get("latitude").getAsString(), 
            location.get("longitude").getAsString(), 
            location.get("country").getAsString(), 
            location.get("city").getAsString(), 
            type,
            marriageYear,
            malePersonId,
            null
        );
        return this.eventAccess.create(
            Util.generateRandomString(),
            location.get("latitude").getAsString(), 
            location.get("longitude").getAsString(), 
            location.get("country").getAsString(), 
            location.get("city").getAsString(), 
            type,
            marriageYear,
            womanPersonId,
            null
        );
    }











    private int generateBirthDate(int year){
        int birthMin = year + 1;
        int birthMax = year + 5;
        return Util.getRandomNumber(birthMin, birthMax);
    }

    private int generateBatismDate(int year){
        int baptismMin = year + 8;
        int baptismMax = year + 15;
        return Util.getRandomNumber(baptismMin, baptismMax);
    }

    private int generateDeathDate(int year){
        int deathMin = year + 25;
        int deathMax = year + 40;
        return Util.getRandomNumber(deathMin, deathMax);
    }













    private String generateFirstName(String gender){
        if(gender == "F"){
            return this.generateFemaleFirstName();
        }else{
            return this.generateMaleFirstName();
        }
    }

    private String generateLastName(){
        String name = "";
        try{
            FileReader reader = new FileReader("library/snames.json");
            JsonObject locations = new JsonParser().parse(reader).getAsJsonObject();
            JsonArray data = locations.get("data").getAsJsonArray();
            int randomIndex = Util.getRandomNumber(1, data.size());
            name = data.get(randomIndex).getAsString();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return name;
    }

    private String generateMaleFirstName(){
        String name = "";
        try{
            FileReader reader = new FileReader("library/mnames.json");
            JsonObject locations = new JsonParser().parse(reader).getAsJsonObject();
            JsonArray data = locations.get("data").getAsJsonArray();
            int randomIndex = Util.getRandomNumber(1, data.size());
            name = data.get(randomIndex).getAsString();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return name;
    }

    private String generateFemaleFirstName(){
        String name = "";
        try{
            FileReader reader = new FileReader("library/fnames.json");
            JsonObject locations = new JsonParser().parse(reader).getAsJsonObject();
            JsonArray data = locations.get("data").getAsJsonArray();
            int randomIndex = Util.getRandomNumber(1, data.size());
            name = data.get(randomIndex).getAsString();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return name;
    }

    private JsonObject generateLocation(){
        JsonObject location  = new JsonObject();
        try{
            FileReader reader = new FileReader("library/locations.json");
            JsonObject locations = new JsonParser().parse(reader).getAsJsonObject();
            JsonArray data = locations.get("data").getAsJsonArray();
            int randomIndex = Util.getRandomNumber(1, data.size());
            location = data.get(randomIndex).getAsJsonObject();
            //use like generateLocation.get("country");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return location;
    }

}