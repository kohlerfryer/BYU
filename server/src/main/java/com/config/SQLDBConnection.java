package com.familymap;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.text.MessageFormat;

/**
* SQl Database connection with helper methods
*/
public class SQLDBConnection implements DBConnection{

    /**DB username*/
    private String username;

    /**DB password*/
    private String password;

    /** DB url*/
    private String databaseURL;

    /** DB driver type*/
    private final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";

    SQLDBConnection(String databaseURL, String username, String password){
        //TODO:: since this data is user driven, check it, create a username and password class
        //server url example -- jdbc:mysql://localhost:3306/database_name
        this.databaseURL = databaseURL;
        this.password = password;
        this.username = username;
    }

    /** 
    * gets a new connection from a DB driver.
    * This should be used evertyine a query
    * is executed.
    * @param databaseURL
    * @return instance of a connection
    */
    private Connection getConnection(String databaseURL) {
        Connection connection = null;
        try {
            //Class.forName(DATABASE_DRIVER);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(databaseURL, this.username, this.password);
        } catch (Exception e) {
            //TODO: handle invalid db url or refused connection
            e.printStackTrace();
        }
        return connection;
    }

    // private void closeConnection(Connection connection) {
    //     try {
    //         connection.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    //due to the simplicity of project specs and small time frame, this will not 
    //take the obvious security mesures such as prottection against sql injection ect...
    private ResultSet executeQuery(String query){
        Connection connection = this.getConnection(this.databaseURL);
        ResultSet result = null;
        try {
            result = connection.prepareStatement(query).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String executeAdd(String query){
        // System.out.println("*******************" + query);
        Connection connection = this.getConnection(this.databaseURL);
        String id = "-1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  
            pstmt.executeUpdate();  
            ResultSet keys = pstmt.getGeneratedKeys();    
            keys.next();  
            id = keys.getString(1);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

     private int executeUpdate(String query){
        Connection connection = this.getConnection(this.databaseURL);
        ResultSet result = null;
	    int rowsAffected = 0;
        try {
            rowsAffected  = connection.prepareStatement(query).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    return rowsAffected;
     }

    public ResultSet getTuple(String relation, String key, String delimeter, String desiredValue){
        String query = MessageFormat.format(
            "SELECT * FROM {0} where {1} {2} {3}",
            relation, key, delimeter, desiredValue);
        ResultSet results = this.executeQuery(query);
        return results;
    }
    
    public boolean updateTuple(String relation, String setStatement, String key, String delimeter, String desiredValue){
        String query = MessageFormat.format(
            "UPDATE {0} SET {1} WHERE {2} {3} {4}",
            relation, setStatement, key, delimeter, desiredValue);
        int rowsAffected  = this.executeUpdate(query);
	    return (rowsAffected > 0);
    }

    public ResultSet getTupleFromJoin(String firstRelation, String secondRelation, String key, String delimeter, String desiredValue){
        return null;
    }

    public String createTuple(String relation, String attributes, String values){
        String id = Util.generateRandomString();
        String query = MessageFormat.format(
            "INSERT INTO {0} (id, {1}) VALUES ('"+ id +"', {2})",
            relation, attributes, values);
        String id  = this.executeAdd(query);
	    return id;
    }

    public int deleteTuple(String relation, String key, String delimeter, String desiredValue){
        String query = MessageFormat.format(
            "DELETE FROM {0} where {1} {2} {3}",
            relation, key, delimeter, desiredValue);
        int rowsAffected  = this.executeUpdate(query);
        System.out.println(query);
	    return rowsAffected;
    }

    public boolean truncateRelation(String relation){
        String query = MessageFormat.format(
            "TRUNCATE {0}",
            relation
        );
        int rowsAffected  = this.executeUpdate(query);
	    return (rowsAffected > 0);
    }

}
