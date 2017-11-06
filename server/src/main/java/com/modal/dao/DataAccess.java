package com.familymap;

import java.sql.ResultSet;
import com.familymap.DBConnection;
    /**
    * API's with DBConnection and returns/manages models
    * this class should know nothing about the type of DB
    * connection. All classes that implement 
    * DBConnections conform to the same set of methods get, create ... 
    * that have the same signatures :)
    */
public class DataAccess{

    /** Reference to Singleton DB*/
    private DBConnection dbConnection;
    
    public DataAccess(DBConnection dbConnection){
        this.dbConnection = dbConnection;
    }
    
//     /** 
//     * gets events with specified value
//     * @param relation desired relation
//     * @param key value identifier of tuple of first Relation on which to project
//     * @param delimeter delimiter used with key
//     * @param desiredValue dilimeted value
//     * @return ResultSet
//     */
    public ResultSet rawGet(String relation, String key, String delimeter, String desiredValue){
        return this.dbConnection.getTuple(relation, key, delimeter, desiredValue);
    }

//     /** 
//     * gets events with specified value
//     * @param relation desired relation
//     * @param key value identifier of tuple of first Relation on which to project
//     * @param delimeter delimiter used with key
//     * @param desiredValue dilimeted value
//     * @return ResultSet
//     */
    public String rawCreate(String relation, String attributes, String values){
        return this.dbConnection.createTuple(relation, attributes, values);
    }

    public boolean rawUpdate(String relation, String changes, String key, String delimeter, String value){
        return this.dbConnection.updateTuple(relation, changes, key, delimeter, value);
    }

    public int rawDelete(String relation, String key, String delimeter, String value){
        return this.dbConnection.deleteTuple(relation, key, delimeter, value);
    }

}