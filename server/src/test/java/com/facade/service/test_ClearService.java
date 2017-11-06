package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Authentication;
import com.familymap.AuthenticationAccess;
import static com.familymap.DBSingleton.*;

//import static com.familymap.DBConnectionFactory.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

import static org.junit.Assert.* ;

public class test_ClearService{

    private ClearService clearService;
    private AuthenticationAccess authenticationAccess;
    final private String userId = "7";

    @Before
    public void setUp() {
	    DBConnection connection = DBSingleton.getInstance();    
        this.clearService = new ClearService();
        this.authenticationAccess = new AuthenticationAccess(connection);
    }

    @Test
    public void testClear() {
        Authentication authentication = this.authenticationAccess.create(this.userId);
        clearService.clearData();
        ArrayList<Authentication> authenticationList = this.authenticationAccess.get("user_id", "=", userId);
        assertTrue(authenticationList.isEmpty());
    }

}

