package com.familymap;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
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
        //JsonObject resultJson;
        ResultSet result = null;
        try {
            result = connection.prepareStatement(query).executeQuery();
            //resultJson = JavaUtil.convertResultSetToJSON(Result);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ResultSet getTuple(String fromRelation, String key, String delimeter, String desiredValue){
        String query = MessageFormat.format(
            "SELECT * FROM {0} where {1} {2} {3}",
            fromRelation, key, delimeter, desiredValue);
        ResultSet results = this.executeQuery(query);
        return results;
    }
    
    public ResultSet updateTuple(String relation, ResultSet tuple){
        return null;
    }

    public ResultSet getTupleFromJoin(String firstRelation, String secondRelation, String key, String delimeter, String desiredValue){
        return null;
    }

    public int addTuple(String relation, ResultSet tuple){
        return 0;
    }

    public void truncateRelation(String relation){}
    public void truncateAllRelations(){}

}