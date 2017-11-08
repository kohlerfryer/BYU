package com.familymap;

import com.familymap.DBConnection;
import com.familymap.Authentication;
import com.familymap.AuthenticationAccess;
import com.familymap.FillRequestBody;
import com.familymap.FillResponseBody;
import com.familymap.FillService;
import com.familymap.UserAccess;
import com.familymap.PersonAccess;
import com.familymap.Person;
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

public class test_FillService{

    private ClearService clearService;
    private AuthenticationAccess authenticationAccess;
    private FillService fillService;
    private FillRequestBody validRequestBody;
    private FillRequestBody invalidRequestBody;

    private UserAccess userAccess;
    private PersonAccess personAccess;

    final private String firstName = "noob";
    final private String lastname = "noober";
    final private String gender = "m";
    final private String fatherId = "1";
    final private String motherId = "2";
    final private String spouseId = "3";
    final private String username = "abu22";
    final private String email = "abu22@gmail.com";
    final private String password = "hashthis";
    final private String descendant = "kdfaskldjf343";
    final private int generations = 4;

    @Before
    public void setUp() {
	    DBConnection connection = DBSingleton.getInstance();
        this.userAccess = new UserAccess(connection);
        this.personAccess = new PersonAccess(connection);
        Person person = this.personAccess.create(this.firstName+this.lastname, this.firstName, this.lastname, this.gender, this.fatherId, this.motherId, this.spouseId, this.descendant);
        this.userAccess.create(this.username, this.email, this.firstName, this.lastname, this.gender, this.password);
        this.fillService = new FillService();
        this.validRequestBody = new FillRequestBody(
            this.username,
            this.generations
        );

    }

    @Test
    public void testSuccessfulFill(){
        FillResponseBody responseBody = this.fillService.fill(this.validRequestBody);
        System.out.println(responseBody.toString());
        assertTrue(false);
        //assertEquals(this.username, responseBody.getUserName());
    }

}

