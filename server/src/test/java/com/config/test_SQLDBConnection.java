package com.familymap;

import com.familymap.test_SQLDBConnection;
import static com.familymap.DBConnectionFactory.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.* ;

public class test_SQLDBConnection {

    // private SQLDBConnection connection;
    
    // @Before
    // public void setUp() {
	//     this.connection = DBConnectionFactory.getMYSQLDBConnection();
    // }

    // public int create(String columns, String values){
    //     return this.connection.createTuple("EventType", columns, values);
    // }

    // public ResultSet read(int id){
    //     return this.connection.getTuple("EventType", "id", "=", Integer.toString(id));
    // }

    // public boolean update(int id, String value){
    //     return this.connection.updateTuple("EventType", "type = '"+ value +"'" , "id", "=", Integer.toString(id));
    // }

    // public boolean delete(int id){
    //     return this.connection.deleteTuple("EventType", "id", "=", Integer.toString(id));
    // }

    // @Test
    // public void testCreate() {
    //     String columns = "type";
    //     String values = "'test'";
    //     int id = this.create(columns, values);
    //     assertNotEquals(id, -1);
    // }

    // @Test
    // public void testRead() throws SQLException {
    //     String columns = "type";
    //     String values = "'readTest'";
    //     int id = this.create(columns, values);
    //     ResultSet result = this.read(id);
    //     assertTrue(result.next());
    //     assertEquals(result.getString("type"), "readTest");
    // }

    // @Test
    // public void testUpdate() throws SQLException {
    //     String columns = "type";
    //     String values = "'asdf'";
	//     String updatedValue = "*****";
    //     int id = this.create(columns, values);
	//     assertTrue(this.update(id, updatedValue));
    // }

    // @Test
    // public void testDelete() throws SQLException{
    //     String columns = "type";
    //     String values = "'%%%%%%%%'";
    //     int id = this.create(columns, values);
	//     assertTrue(this.delete(id));
    // }

}

