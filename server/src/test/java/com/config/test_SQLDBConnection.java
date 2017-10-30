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
    public void testGetTuple() {
        ResultSet result = this.connection.getTuple("EventType", "id", "=", "1");

        System.out.println("");
        System.out.println("");
        System.out.println("********************");
        System.out.println("");
        System.out.println("");


	    assertNotNull("\n\n\n hello \n\n\n", null);

    }

    @Test
    public void testInvalidWords() {
	assertFalse(false);
    }

}

