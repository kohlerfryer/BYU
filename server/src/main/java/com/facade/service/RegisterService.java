package com.familymap;

public class RegisterService extends FamilyMapService{

    public RegisterService(DBConnection dbConnection){
        super(dbConnection);
    }

    public JsonObject register(){
        for (String relation : this.relations) {
            this.dbConnection.truncateRelation(relation);
        }
    }

}