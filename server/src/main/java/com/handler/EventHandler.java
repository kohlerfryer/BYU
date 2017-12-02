package com.familymap;

import com.familymap.DBConnection;
import com.familymap.FamilyMapHandler;
import com.familymap.RegisterResponseBody;
import com.familymap.RegisterRequestBody;
import com.familymap.EventsService;

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


public class EventHandler extends FamilyMapHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
        //if (!exchange.getRequestMethod().toLowerCase().equals("post"))return;
            String pathInfo = exchange.getRequestURI().getPath();
            String[] pathVariables = pathInfo.split("/");


        try{
            Headers reqHeaders = exchange.getRequestHeaders();
            String token = reqHeaders.getFirst("Authorization");
            
            if(pathVariables.length == 3){
                String eventId = pathVariables[2];
                handleEvent(exchange, token, eventId);
            }else{
                handleEvents(exchange, token);
            }

        }catch(Exception e){
             e.printStackTrace();
             exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }
        exchange.getResponseBody().close();
    
    }

    public void handleEvent(HttpExchange exchange, String token, String eventId) throws IOException {

        EventRequestBody requestBody = new EventRequestBody(eventId, token);             
        EventService eventService = new EventService();
        EventResponseBody responseBody = eventService.getEvent(requestBody);

        if(responseBody.wasSuccessfull()){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_ACCEPTED, 0);
        }
        else{
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }
        this.writeStringToOutputStream(responseBody.toJsonString(), exchange.getResponseBody());
    }

    public void handleEvents(HttpExchange exchange, String token) throws IOException {

        EventRequestBody requestBody = new EventRequestBody(token);             
        EventsService eventsService = new EventsService();
        EventsResponseBody responseBody = eventsService.getEvents(requestBody);

        if(responseBody.wasSuccessfull()){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_ACCEPTED, 0);
        }
        else{
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
        System.out.println(responseBody.toJsonString());
        this.writeStringToOutputStream(responseBody.toJsonString(), exchange.getResponseBody());
    }

}