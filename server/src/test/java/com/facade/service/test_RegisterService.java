package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Authentication;
import com.familymap.AuthenticationAccess;
import com.familymap.RegisterRequestBody;
import com.familymap.RegisterResponseBody;
import com.familymap.RegisterService;
import com.familymap.InvalidRequestException;
import com.google.gson.Gson;


//import static com.familymap.DBConnectionFactory.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

import static org.junit.Assert.* ;

public class test_RegisterService{

    private ClearService clearService;
    private AuthenticationAccess authenticationAccess;
    private RegisterService registerService;
    private RegisterRequestBody validRequestBody;
    private RegisterRequestBody invalidRequestBody;
    final private String username = "mkfryer";

    @Before
    public void setUp() {
        this.registerService = new RegisterService();
        this.validRequestBody = new RegisterRequestBody(
            this.username,
            "secret_password",
            "noob@gmail.com",
            "johny",
            "bravo",
            "M"
        );

    }

    @Test
    public void testRegister(){
        RegisterResponseBody responseBody = this.registerService.register(this.validRequestBody);
        assertEquals(this.username, responseBody.getUserName());
        responseBody = this.registerService.register(this.validRequestBody);
        assertEquals(null, responseBody.getUserName());
    }

}

