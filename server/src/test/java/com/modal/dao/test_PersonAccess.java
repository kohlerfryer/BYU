package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Person;
import com.familymap.PersonAccess;

import static com.familymap.DBConnectionFactory.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

import static org.junit.Assert.* ;

public class test_PersonAccess{

    private PersonAccess personAccess;
    final String firstName = "noob";
    final String lastname = "noober";
    final String gender = "m";
    final String fatherId = "1";
    final String motherId = "2";
    final String spouseId = "3";

    @Before
    public void setUp() {
	SQLDBConnection connection = DBConnectionFactory.getMYSQLDBConnection();
        this.personAccess = new PersonAccess(connection);
    }

    @Test
    public void testCreate() {
        Person person = this.personAccess.create(this.firstName, this.lastname, this.gender, this.fatherId, this.motherId, this.spouseId);
        assertEquals(person.getFatherId(), this.fatherId);
    }

    @Test
    public void testRead() throws SQLException {
        Person person = this.personAccess.create(this.firstName, this.lastname, this.gender, this.fatherId, this.motherId, this.spouseId);
        ArrayList<Person> personDuplicateList = this.personAccess.get("id", "=", person.getId());
        assertEquals(person.getId(), personDuplicateList.get(0).getId());
    }

}

