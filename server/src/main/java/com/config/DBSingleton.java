package com.familymap;

import com.familymap.DBConnection;
import com.familymap.SQLDBConnection;

public class DBSingleton{
    private static DBConnection db = null;
    private static final String MYSQLConnectionURL = "jdbc:mysql://localhost:3306/FamilyMap";
    private static final String MYSQLConnectionUsername = "FamilyMapApp";
    private static final String MYSQLConnectionPassword = "FamilyMapApp";

    protected DBSingleton() {
    // Exists only to defeat instantiation.
    }

    public static DBConnection getInstance() {
        //put url here up above
        if(db == null) {
            db = new SQLDBConnection(MYSQLConnectionURL, MYSQLConnectionUsername, MYSQLConnectionPassword);
        }
        return db;
    }
}