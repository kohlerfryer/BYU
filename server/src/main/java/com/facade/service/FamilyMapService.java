package com.familymap;

import com.familymap.DBSingleton;
import com.familymap.DBConnection;

public class FamilyMapService{
    protected DBConnection dbConnection;

    public FamilyMapService(){
        this.dbConnection = DBSingleton.getInstance();
    }

    //boolean hasValidAuthToken(HttpExchange exchange) {
        //getkey
        //check time stamp
        
        // String method = exchange.getRequestMethod();
                
        // URI uri = exchange.getRequestURI();
        // System.out.println("uri: " + uri);
                
        // String auth = exchange.getRequestHeaders().getFirst("Authorization");
        // System.out.println("auth: " + auth);

        // System.out.println();

        //printHeaders(exchange.getRequestHeaders());
    //}

//    private authenticateRequest();

}