package com.familymap;

import com.familymap.DBConnection;
import static com.familymap.DBSingleton.*;
import static com.familymap.Util.*;
import com.familymap.Person;
import com.familymap.PersonAccess;
import com.familymap.EventAccess;
import java.util.ArrayList;
import java.util.Random;

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

    public void setIndividualData(Person person, int year){


        // int marriageMin = year + 15;
        // int marriageMax = year + 22;
        // int marriageDate = random.nextInt(marriageMax-marriageMin+1)+marriageMin;
        //System.out.println("*************" + birthDate);



    }

    public void setMarriageData(){
        
    }

    public int generateBirthDate(int year){
        int birthMin = year + 1;
        int birthMax = year + 5;
        return this.random.nextInt(birthMax-birthMin+1)+birthMin;
    }

    public int generateBatismDate(int year){
        int baptismMin = year + 8;
        int baptismMax = year + 15;
        return this.random.nextInt(baptismMax-baptismMin+1)+baptismMin;
    }

    public int generateDeathDate(int year){
        int deathMin = year + 25;
        int deathMax = year + 40;
        return this.random.nextInt(deathMax-deathMin+1)+deathMin;
    }

    public void setAncestorData(Person person, int year){

        int birthDate = this.generateBirthDate(year);
        int deathDate = this.generateDeathDate(birthDate);  

        if(Math.random() < 0.5){
            int baptismDate = this.generateBatismDate(birthDate);  
        }

    }

    public void setPersonData(Person person, int year){
        int birthDate = this.generateBirthDate(year);
        int deathDate = this.generateDeathDate(birthDate);  

    }

    public void generatePersonData(Person person, int generations, int year){
        year-=40;
        ArrayList<Person> result = this.personAccess.get("id", "=", person.getId());
        Person currentPerson = result.get(0);
        this.setPersonData(currentPerson, year);
        Person mother = generateParentData(person.getLastName(), person.getGender(), generations -1, year);
        Person father = generateParentData(person.getLastName(), person.getGender(), generations -1, year);
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
        Person currentPerson = this.personAccess.create(this.generateFirstName(gender), lastName, gender, null, null, null);
        this.setAncestorData(currentPerson, year);
        if(generations >  0){
            Person mother = generateParentData(lastName, "F", generations -1, year);
            Person father = generateParentData(lastName, "M", generations -1, year);
            father.setSpouseId(mother.getId());
            mother.setSpouseId(father.getId());
            currentPerson.setMotherId(mother.getId());
            currentPerson.setFatherId(father.getId());
            //this.setMarriageData(father, mother);
            this.personAccess.update(father);
            this.personAccess.update(mother);
            this.personAccess.update(currentPerson);
        }
        return currentPerson;
    }

    private String generateFirstName(String gender){
        return Util.generateFirstName(gender);
    }

}