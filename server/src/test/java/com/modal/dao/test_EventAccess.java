package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Event;
import static com.familymap.DBConnectionFactory.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

import static org.junit.Assert.* ;

public class test_EventAccess {

    private EventAccess eventAccess;
    final String lattitude = "10000000";
    final String longitude = "2000000";
    final String country = "Peru";
    final String city = "Arequipa";
    final String type = "wedding";
    final String updatedType = "divorce";
    final String year = "2017";

    @Before
    public void setUp() {
	SQLDBConnection connection = DBConnectionFactory.getMYSQLDBConnection();
        this.eventAccess = new EventAccess(connection);
    }

    @Test
    public void testCreate() {
        Event event = this.eventAccess.create(this.lattitude, this.longitude, this.country, this.city, this.type, this.year);
        assertEquals(event.getCountry(), this.country);
    }

    @Test
    public void testRead() throws SQLException {
        Event event = this.eventAccess.create(this.lattitude, this.longitude, this.country, this.city, this.type, this.year);
        ArrayList<Event> eventDuplicateList = this.eventAccess.get("id", "=", event.getId());
        assertEquals(event.getId(), eventDuplicateList.get(0).getId());
    }

    @Test
    public void testUpdate() throws SQLException {
        Event event = this.eventAccess.create(this.lattitude, this.longitude, this.country, this.city, this.type, this.year);
        event.setType(this.updatedType);
	    assertTrue(this.eventAccess.update(event));
    }

    @Test
    public void testDelete() throws SQLException{
        Event event = this.eventAccess.create(this.lattitude, this.longitude, this.country, this.city, this.type, this.year);
	    assertTrue(this.eventAccess.delete(event));
    }

}

