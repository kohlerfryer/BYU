package com.familymap;

import com.familymap.SQLDBConnection;

/**
* for now, just used for unit tests
* could to be extendable in future to 
* handle various DBConnections
*/
public class DBConnectionFactory {
    private static final String MYSQLConnectionURL = "jdbc:mysql://localhost:3306/FamilyMap";
    private static final String MYSQLConnectionUsername = "FamilyMapApp";
    private static final String MYSQLConnectionPassword = "FamilyMapApp";

    /** 
    * returns SQLDBConnection
    * keeps all passwords, usernames ect in one place
    */
    public static SQLDBConnection getMYSQLDBConnection() {
        return new SQLDBConnection(MYSQLConnectionURL, MYSQLConnectionUsername, MYSQLConnectionPassword);
    }
}