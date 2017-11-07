package com.familymap;

import com.familymap.DBConnection;
import com.familymap.User;
import com.familymap.UserAccess;

import static com.familymap.DBSingleton.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

import static org.junit.Assert.* ;

public class test_UserAccess{

    private UserAccess userAccess;
    final String username = "fakeUsername";
    final String password = "hashme";
    final String email = "mailme@mail.com";
    final String firstName = "aaa";
    final String lastName = "bbb";
    final String gender = "M";
    private String id;
//    public User create(String username, String email, String personId, String password){

    @Before
    public void setUp() {
	    DBConnection connection = DBSingleton.getInstance();
        this.userAccess = new UserAccess(connection);
        this.id = Util.generateRandomString();
    }
//    public User create(String id, String username, String email, String firstName, String lastName, String gender, String password){

    @Test
    public void testCreate() {
        User user = this.userAccess.create(this.id, this.username, this.email, this.firstName, this.lastName, this.gender, this.password);
        assertEquals(user.getId(), this.id);
    }

    @Test
    public void testRead() throws SQLException {
        User user = this.userAccess.create(this.id, this.username, this.email, this.firstName, this.lastName, this.gender, this.password);
        ArrayList<User> userDuplicateList = this.userAccess.get("id", "=", user.getId());
        assertEquals(user.getId(), userDuplicateList.get(0).getId());
    }

}

