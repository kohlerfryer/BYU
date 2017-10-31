package com.familymap;

import com.familymap.test_SQLDBConnection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import org.junit.* ;

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

    public int create(String columns, String values){

        int eventID = this.connection.addTuple("EventType", columns, values);
        return eventID;
    }

    public ResultSet read(int id){
        return this.connection.getTuple("EventType", "id", "=", Integer.toString(id));
    }

    public int update(int id){
        return this.connection.updateTuple("EventType", "type='newType'" , "id", "=", Integer.toString(id));
    }

    public void delete(int id){
        this.connection.deleteTuple("EventType", "id", "=", Integer.toString(id));
    }

    @Test
    public void testCreate() {
        String columns = "type";
        String values = "'test'";
        int id = this.create(columns, values);
        assertNotEquals(id, -1);
    }

    @Test
    public void testRead() throws SQLException {
        String columns = "type";
        String values = "'readTest'";
        int id = this.create(columns, values);
        ResultSet result = this.read(id);
        assertTrue(result.next());
        assertEquals(result.getString("type"), "readTest");

    }

    // @Test
    // public void testUpdate() {
    //     ResultSet result = this.connection.getTuple("EventType", "id", "=", Integer.toString(eventID));
    //     ResultSet readResult = this.read(result.getInt("id"));
    //     int updatedEventTypeId = update(createdEventTypeId);
    //     ResultSet updatedResult = this.read(updatedEventTypeId);
    //     assertFalse(result.getString("type").equals(updatedResult.getString("type")));
    // }

    // rowDeleted()

    // @Test
    // public void testCRUD() throws SQLException {
    //     //create
    //     int createdEventTypeId = this.create();
    //     assertNotEquals(createdEventTypeId, -1);
    //     //read
    //     ResultSet result = this.read(createdEventTypeId);
    //     assertTrue(result.isBeforeFirst());
    //     //update
    //     int updatedEventTypeId = update(createdEventTypeId);
    //     ResultSet updatedResult = this.read(updatedEventTypeId);
    //     assertFalse(result.getString("type").equals(updatedResult.getString("type")));
    //     //delete
    //     // delete(updatedEventTypeId);
    //     // ResultSet deletedResult = this.read(createdEventTypeId);
    //     // assertFalse(deletedResult.isBeforeFirst());

    // }
    
    // @Test
    // public void testRead() {
    //     ResultSet result = this.connection.getTuple("EventType", "id", "=", Integer.toString(eventID));
    //     assertTrue(result.isBeforeFirst());
    // }

    // @Test
    // public void testUpdate() {
	// assertFalse(true);
    // }

    // @Test
    // public void testDelete() {
	// assertFalse(false);
    // }

}

