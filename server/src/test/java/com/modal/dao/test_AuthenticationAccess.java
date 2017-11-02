package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Authentication;
import com.familymap.AuthenticationAccess;

import static com.familymap.DBConnectionFactory.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

import static org.junit.Assert.* ;

public class test_AuthenticationAccess{

    private AuthenticationAccess authenticationAccess;
    final String userId = "1";

    @Before
    public void setUp() {
	SQLDBConnection connection = DBConnectionFactory.getMYSQLDBConnection();
        this.authenticationAccess = new AuthenticationAccess(connection);
    }

    @Test
    public void testCreate() {
        Authentication authentication = this.authenticationAccess.create(this.userId);
        assertEquals(authentication.getUserId(), this.userId);
    }

    @Test
    public void testRead() throws SQLException {
        Authentication authentication = this.authenticationAccess.create(this.userId);
        ArrayList<Authentication> authenticationDuplicateList = this.authenticationAccess.get("id", "=", authentication.getId());
        assertEquals(authentication.getId(), authenticationDuplicateList.get(0).getId());
    }

}

