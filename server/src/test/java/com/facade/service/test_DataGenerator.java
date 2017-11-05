package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Person;
import com.familymap.PersonAccess;
import com.familymap.DataGenerator;

import static com.familymap.DBSingleton.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

import static org.junit.Assert.* ;

public class test_DataGenerator{

    private PersonAccess personAccess;
    Person currentPerson;
    DataGenerator dataGenerator;
    final String firstName = "Kohler";
    final String lastname = "Fryer";
    final String gender = "m";
    final String fatherId = "1";
    final String motherId = "2";
    final String spouseId = "3";

    @Before
    public void setUp() {
	    DBConnection connection = DBSingleton.getInstance();
        this.personAccess = new PersonAccess(connection);
        this.currentPerson = this.personAccess.create(this.firstName, this.lastname, this.gender, this.fatherId, this.motherId, this.spouseId);
        this.dataGenerator = new DataGenerator();
    }

    @Test
    public void testGenerate() {
       dataGenerator.generatePersonData(this.currentPerson,4, 2000);
       assertTrue(false);
    }

    // @Test
    // public void testRead() throws SQLException {
    //     Person person = this.personAccess.create(this.firstName, this.lastname, this.gender, this.fatherId, this.motherId, this.spouseId);
    //     ArrayList<Person> personDuplicateList = this.personAccess.get("id", "=", person.getId());
    //     assertEquals(person.getId(), personDuplicateList.get(0).getId());
    // }

}

