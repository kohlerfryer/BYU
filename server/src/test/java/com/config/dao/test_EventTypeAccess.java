package com.familymap;

import com.familymap.test_SQLDBConnection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.* ;

public class test_EventTypeAccess{

     private final String connectionURL = "jdbc:mysql://localhost:3306/FamilyMap";
     private final String connectionUsername= "FamilyMapApp";
     private final String connectionPassword = "FamilyMapApp";
     private EventTypeAccess eventTypeAccess;
     //MAKE SINGLETON DB OBJECT
  
     @Before
     public void setUp() {
 	 SQLDBConnection connection = new SQLDBConnection(connectionURL, connectionUsername, connectionPassword);
         this.eventTypeAccess = new EventTypeAccess(connection);
     }

    // @Test
    // public void testCRUD() {
    //     //CREATE
    //     String columns = "type";
    //     String values = "'asdf'";
    //     //READ
    //     int eventID = this.connection.addTuple("EventType", columns, values);
    //     assertNotEquals("db not returning id", eventID, -1);
    //     int eventIDCopy = this.connection.getTuple("EventType", "id", "=", Integer.toString(eventID));
    //     assertEquals(eventID, eventIDCopy);
    //     //UPDATE
	
    // }
    
    // @Test
    // public void testGetTuple() {
    //     int eventID = this.connection.addTuple("EventType", "type", "'test'");
    //     ResultSet result = this.connection.getTuple("EventType", "id", "=", "1");
	
    // }

    // @Test
    // public void testInvalidWords() {
	// assertFalse(false);
    // }

}

