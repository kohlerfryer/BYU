package com.familymap;

import com.familymap.ClearResponseBody;

public class ClearService extends FamilyMapService{
    UserAccess userAccess;
    EventAccess eventAccess;
    PersonAccess personAccess;
    DataGenerator dataGenerator;
    //I recognize a better approach would be to get all the table names from the root schema db
    //and to truncate them. For lack of time, this will have to do. 
    final String[] relations = {"Authentication", "Event", "Person", "User"};

    public ClearService(){
        super();
    }

    public ClearResponseBody clearData(){
        ClearResponseBody responseBody;
        boolean success = true;
        try{
            for (String relation : this.relations) {
                this.dbConnection.truncateRelation(relation);
            }            
            responseBody = new ClearResponseBody("Clear succeeded.", success);
        }catch(NullPointerException e){
            responseBody = new ClearResponseBody("Missing parameters", success);
            e.printStackTrace();
        }
        return responseBody;

    }

}
