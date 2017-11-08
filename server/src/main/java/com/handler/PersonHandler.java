package com.familymap;

// import java.io.*;
// import java.net.*;
// import com.sun.net.httpserver.*;
import com.familymap.DBConnection;
import com.familymap.FamilyMapHandler;
import com.familymap.RegisterResponseBody;
import com.familymap.RegisterRequestBody;
import com.familymap.PersonsService;

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
            String pathInfo = exchange.getRequestURI().getPath();
            String[] pathVariables = pathInfo.split("/");


        try{
            Headers reqHeaders = exchange.getRequestHeaders();
            String token = reqHeaders.getFirst("Authorization");
            
            if(pathVariables.length == 3){
                String personId = pathVariables[2];
                handlePerson(exchange, token, personId);
            }else{
                handlePersons(exchange, token);
            }

        }catch(Exception e){
             e.printStackTrace();
             exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }
        exchange.getResponseBody().close();
    
    }

    public void handlePerson(HttpExchange exchange, String token, String personId) throws IOException {

        PersonRequestBody requestBody = new PersonRequestBody(personId, token);             
        PersonService personService = new PersonService();
        PersonResponseBody responseBody = personService.getPerson(requestBody);

        if(responseBody.wasSuccessfull()){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        }
        else{
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
        this.writeStringToOutputStream(responseBody.toJsonString(), exchange.getResponseBody());
    }

    public void handlePersons(HttpExchange exchange, String token) throws IOException {

        PersonRequestBody requestBody = new PersonRequestBody(token);             
        PersonsService personsService = new PersonsService();
        PersonsResponseBody responseBody = personsService.getPersons(requestBody);

        if(responseBody.wasSuccessfull()){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        }
        else{
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
        this.writeStringToOutputStream(responseBody.toJsonString(), exchange.getResponseBody());
    }

}