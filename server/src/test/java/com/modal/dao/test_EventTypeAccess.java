package com.familymap;

import com.familymap.DBConnection;
import com.familymap.EventType;
import static com.familymap.DBConnectionFactory.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.* ;

public class test_EventTypeAccess {

    private EventTypeAccess eventTypeAccess;
    final String initialValues = "wedding";
    final String updatedValues = "Divorce";


    @Before
    public void setUp() {
	    SQLDBConnection connection = DBConnectionFactory.getMYSQLDBConnection();
        this.eventTypeAccess = new EventTypeAccess(connection);
    }

    @Test
    public void testCreate() {
        EventType eventType = this.eventTypeAccess.create(this.initialValues);
        assertEquals(eventType.getType(), this.initialValues);
    }

    @Test
    public void testRead() throws SQLException {
        EventType eventType = this.eventTypeAccess.create(this.initialValues);
        EventType eventTypeDuplicate = this.eventTypeAccess.get("id", "=", Integer.toString(eventType.getId()));
        assertEquals(eventType.getId(), eventTypeDuplicate.getId());
    }

    @Test
    public void testUpdate() throws SQLException {
        EventType eventType = this.eventTypeAccess.create(this.initialValues);
        eventType.setType(this.updatedValues);
	    assertTrue(this.eventTypeAccess.update(eventType));
    }

    @Test
    public void testDelete() throws SQLException{
        EventType eventType = this.eventTypeAccess.create(this.initialValues);
	    assertTrue(this.eventTypeAccess.delete(eventType));
    }

}

