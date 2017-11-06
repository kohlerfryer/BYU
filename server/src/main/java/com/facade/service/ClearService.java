package com.familymap;

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
        FillResponseBody responseBody;
        try{
            requestBody.validate(this.userAccess);
            for (String relation : this.relations) {
                this.dbConnection.truncateRelation(relation);
            }            
            responseBody = new ClearResponseBody("Clear succeeded.");
        }catch(InvalidRequestException e){
            responseBody = new ClearResponseBody(e.getMessage());
        }catch(NullPointerException e){
            responseBody = new ClearResponseBody("Missing parameters");
            e.printStackTrace();
        }
        return responseBody;

    }

}
