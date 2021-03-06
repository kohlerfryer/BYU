package com.familymap;

// import java.io.*;
// import java.net.*;
// import com.sun.net.httpserver.*;
import com.familymap.DBConnection;
import com.familymap.FamilyMapHandler;
import com.familymap.RegisterResponseBody;
import com.familymap.RegisterRequestBody;

import java.net.HttpURLConnection;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.Headers;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;


public class ClearHandler extends FamilyMapHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().toLowerCase().equals("post"))return; 
        
        try{
            ClearService clearService = new ClearService();
            Headers reqestHeaders = exchange.getRequestHeaders();
            ClearResponseBody responseBody = clearService.clear();

            if(responseBody.wasSuccessfull()){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            }
            this.writeStringToOutputStream(responseBody.toJsonString(), exchange.getResponseBody());

        }catch(Exception e){
             e.printStackTrace();
             exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }
        exchange.getResponseBody().close();
    
    }

}