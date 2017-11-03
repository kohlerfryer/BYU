package com.familymap;

// import java.io.*;
// import java.net.*;
// import com.sun.net.httpserver.*;
import com.familymap.DBConnection;
import com.familymap.FamilyMapHandler;
import com.familymap.ResponseBodyWrapper;

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


public class RegisterHandler extends FamilyMapHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().toLowerCase().equals("post"))return; 
    
        try{
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            String requestBody = this.getRequestBody(exchange);
            JsonObject registerRequestBody = parser.parse(requestBody).getAsJsonObject();

            RegisterService registerService = new RegisterService(dbConnection);
            Headers reqestHeaders = exchange.getRequestHeaders();


            ResponseBodyWrapper responseBodyWrapper = registerService.register(registerRequestBody);
            this.writeStringToOutputStream(responseBodyWrapper.getResponseBody(), exchange.getResponseBody());

            if(!responseBodyWrapper.responseTypeSuccessfull()){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
            else if(responseBodyWrapper.responseTypeSuccessfull()){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            }

        }catch(Exception e){
             exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }
        exchange.getResponseBody().close();
    
    }

}