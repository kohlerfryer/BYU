package com.familymap;

import com.familymap.test_SQLDBConnection;
import java.util.*;
import org.junit.* ;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.junit.Assert.* ;

public class test_SQLDBConnection {

    private SQLDBConnection connection;
    private final String connectionURL = "jdbc:mysql://localhost:3306/FamilyMap";
    private final String connectionUsername= "FamilyMapApp";
    private final String connectionPassword = "FamilyMapApp";
    
    
    @Before
    public void setUp() {
	    this.connection = new SQLDBConnection(connectionURL, connectionUsername, connectionPassword);
    }

    @Test
    public void testCRUD() {
	//CREATE
	String columns = "type";
	String values = "'asdf'";
	//READ
        int eventID = this.connection.addTuple("EventType", columns, values);
        assertNotEquals("db not returning id", eventID, -1);
	int eventIDCopy = this.connection.getTuple("EventType", "id", "=", Integer.toString(eventID));
	assertEquals(eventID, eventIDCopy);
	//UPDATE
	
    }
    
    @Test
    public void testGetTuple() {
        int eventID = this.connection.addTuple("EventType", "type", "'test'");
        ResultSet result = this.connection.getTuple("EventType", "id", "=", "1");
	
    }

    @Test
    public void testInvalidWords() {
	assertFalse(false);
    }

}

