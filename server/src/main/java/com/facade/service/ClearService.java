package com.familymap;

public class ClearService extends FamilyMapService{

    //I recognize a better approach would be to get all the table names from the root schema db
    //and to truncate them. For lack of time, this will have to do. 
    final String[] relations = {"Authentication", "Event", "Person", "User"};

    public ClearService(){

    }

    public void clearData(){
        for (String relation : this.relations) {
            this.dbConnection.truncateRelation(relation);
        }
    }

}