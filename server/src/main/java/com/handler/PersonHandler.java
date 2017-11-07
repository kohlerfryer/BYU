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


public class PersonHandler extends FamilyMapHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
        //if (!exchange.getRequestMethod().toLowerCase().equals("post"))return; 
        
        try{
            String pathInfo = exchange.getRequestURI().getPath();
            String[] pathVariables = pathInfo.split("/");
            if((pathVariables.length == 3){
                String personId = pathVariables[2];
            }
            Headers reqHeaders = exchange.getRequestHeaders();
            String authToken = reqHeaders.getFirst("Authorization");

            System.out.println(pathVariables.length );
            // FillService fillService = new FillService();
            // FillRequestBody requestBody;

            // if(pathVariables.length == 4){
            //     requestBody = new FillRequestBody(pathVariables[2], Integer.valueOf(pathVariables[3]));
            // }
            // else{
            //     requestBody = new FillRequestBody(pathVariables[2]);
            // }
            // FillResponseBody responseBody = fillService.fill(requestBody);

            // if(responseBody.wasSuccessfull()){
            //     exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            // }
            // else{
            //     exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            // }
            // this.writeStringToOutputStream(responseBody.toJsonString(), exchange.getResponseBody());

        }catch(InvalidRequestException e){
            responseBody = new PersonResponseBody(e.getMessage());
            this.writeStringToOutputStream(responseBody.toJsonString(), exchange.getResponseBody());
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }catch(Exception e){
             e.printStackTrace();
             exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }
        exchange.getResponseBody().close();
    
    }

}