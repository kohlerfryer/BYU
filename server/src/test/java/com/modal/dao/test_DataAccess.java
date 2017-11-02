package com.familymap;

import com.familymap.DBConnection;
import com.familymap.DataAccess;
import static com.familymap.DBConnectionFactory.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.* ;

public class test_DataAccess {

    // private DataAccess dataAccess;
    // final String initialValues = "'initial'";
    // final String initialComparisonValue = "initial";
    // final String updatedValues = "'updated'";
    // final String updateQuery = "type = 'updated'";
    // final String attributes = "type";
    // final String relation = "EventType";
    // final String delimeter = "=";
    // final String key = "id";

    // @Before
    // public void setUp() {
	//     SQLDBConnection connection = DBConnectionFactory.getMYSQLDBConnection();
    //     this.dataAccess = new DataAccess(connection);
    // }

    // @Test
    // public void testCreate() {
    //     String id = this.dataAccess.create(this.relation, this.attributes, this.initialValues);
    //     assertNotEquals(id, "-1");
    // }

    // @Test
    // public void testRead() throws SQLException {
    //     String id = this.dataAccess.create(this.relation, this.attributes, this.initialValues);
    //     ResultSet result = this.dataAccess.get(this.relation, this.key, this.delimeter, id);
    //     assertTrue(result.next());
    //     assertEquals(result.getString(this.attributes), this.initialComparisonValue);
    // }

    // @Test
    // public void testUpdate() throws SQLException {
    //     String id = this.dataAccess.create(this.relation, this.attributes, this.initialValues);
	//     assertTrue(this.dataAccess.update(this.relation, this.updateQuery, this.key, this.delimeter, id));
    // }

    // @Test
    // public void testDelete() throws SQLException{
    //     String id = this.dataAccess.create(this.relation, this.attributes, this.initialValues);
	//     assertTrue(this.dataAccess.delete(this.relation, this.key, this.delimeter, id));
    // }

}

