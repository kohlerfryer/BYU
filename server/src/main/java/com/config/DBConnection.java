package com.familymap;

import java.sql.ResultSet;
import java.sql.Connection;
import java.util.Properties;


/**
* Abstracts CRUD interaction with DB
* Interface for all types of DB connections. 
* All DB connections that implement this 
* interface should use the Singleton Pattern.
*/
public interface DBConnection{

    /** 
    * closes a db connection
    * @return void
    */
    // private void closeConnection(Connection connection);
    
    /** 
    * gets tuple with specified value
    * @param relation  name of first relation in DB
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return JsonObject Jsonified version of tuple
    */
    public ResultSet getTuple(String relation, String key, String delimeter, String desiredValue);

    /**
    * gets first relation with specified value and then joins
    * with other relation. Assumes that the foreign key's
    * are of form *foreignTableName*_id
    * @param firstRelation  name of first relation in DB
    * @param secondRelation name of second relation in DB
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value
    * @return JsonObject Jsonified version of tuple
    */
    public ResultSet getTupleFromJoin(String firstRelation, String secondRelation, String key, String delimeter, String desiredValue);

    /** 
    * Updates current existing tuple in DB
    * @param relation  name of relation in DB
    * @param tuple Jsonified version of tuple to update
    * @return JsonObject Jsonified version of tuple
    */
    public boolean updateTuple(String relation, String setStatement, String key, String delimeter, String desiredValue);

    /** 
    * Updates current existing tuple in DB
    * @param relation  name of relation in DB
    * @param tuple Jsonified version of tuple to update
    * @return int id of added row
    */
    public String createTuple(String relation, String attributes, String values);

    /** 
    * truncates relation in DB
    * @param relation  name of relation in DB
    * @return void
    */
    public void truncateRelation(String relation);

    /** 
    * truncates all relations in DB
    * @param relation  name of relation in DB
    * @return void
    */
    public void truncateAllRelations();

    /** 
    * deletes all identified tuples
    * @param relation name of first relation in DB
    * @param key value identifier of tuple of first Relation on which to project
    * @param delimeter delimiter used with key
    * @param desiredValue dilimeted value    
    * @return boolean if deletion was successfull
    */
    public boolean deleteTuple(String relation, String key, String delimeter, String desiredValue);
}
