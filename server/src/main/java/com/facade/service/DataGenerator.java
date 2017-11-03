package com.familymap;

import com.familymap.DBConnection;
import static com.familymap.DBSingleton.*;
import com.familymap.Person;
import com.familymap.PersonAccess;
import java.util.ArrayList;

public class DataGenerator{

    private PersonAccess personAccess;

    public DataGenerator(){
	    DBConnection connection = DBSingleton.getInstance();
        this.personAccess = new PersonAccess(connection);
    }

    public void generatePersonData(String personId, int generations){
        ArrayList<Person> result = this.personAccess.get("id", "=", personId);
        Person currentPerson = result.get(0);
        Person mother = generateParentData("F", generations -1);
        Person father = generateParentData("M", generations -1);
        father.setSpouseId(mother.getId());
        mother.setSpouseId(father.getId());
        currentPerson.setMotherId(mother.getId());
        currentPerson.setFatherId(father.getId());
        this.personAccess.update(currentPerson);
        this.personAccess.update(father);
        this.personAccess.update(mother);
    }

    private Person generateParentData(String gender, int generations){
        Person currentPerson = this.personAccess.create(this.generateFirstName(gender), this.generateLastName(), gender);
        //this.setIndividualData(currentPerson);
        if(generations >= 1){
            Person mother = generateParentData("F", generations -1);
            Person father = generateParentData("M", generations -1);
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
        if(gender == "F"){
            return "Eve";
        }else{
            return "Adam";
        }
    }

    private String generateLastName(){
        return "guadalupe";
    }

}