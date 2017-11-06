package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Authentication;
import com.familymap.AuthenticationAccess;
import com.familymap.LoginRequestBody;
import com.familymap.LoginResponseBody;
import com.familymap.LoginService;
import com.familymap.UserAccess;
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

public class test_LoginService{

    private ClearService clearService;
    private AuthenticationAccess authenticationAccess;
    private LoginService loginService;
    private LoginRequestBody validRequestBody;
    private LoginRequestBody invalidRequestBody;
    private UserAccess userAccess;
    final private String username = "abu22";
    final private String email = "abu22@gmail.com";
    final private String personId = "3";
    final private String password = "hashthis";
    final private String invalidPassword = "asdfasasdf";

    @Before
    public void setUp() {
	    DBConnection connection = DBSingleton.getInstance();
        this.userAccess = new UserAccess(connection);
        User user = this.userAccess.create(this.username, this.email, this.personId, this.password);
        this.loginService = new LoginService();
        this.validRequestBody = new LoginRequestBody(
            this.username,
            this.password
        );
        this.invalidRequestBody = new LoginRequestBody(
            this.username,
            this.invalidPassword
        );

    }

    @Test
    public void testSuccessfulLogin(){
        LoginResponseBody responseBody = this.loginService.login(this.validRequestBody);
        assertEquals(this.username, responseBody.getUserName());
    }

    @Test
    public void testUnsuccessfulLogin(){
        LoginResponseBody responseBody = this.loginService.login(this.invalidRequestBody);
        assertNotEquals(this.username, responseBody.getUserName());
    }

}

