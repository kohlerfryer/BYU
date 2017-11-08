package com.familymap;


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


public class RegisterHandler extends FamilyMapHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().toLowerCase().equals("post"))return; 
        
        try{
            Gson gson = new Gson();
            String requestBodyString = this.getRequestBody(exchange);
            RegisterRequestBody requestBody = gson.fromJson(requestBodyString, RegisterRequestBody.class); 
            RegisterService registerService = new RegisterService();
            Headers reqestHeaders = exchange.getRequestHeaders();
            RegisterResponseBody responseBody = registerService.register(requestBody);

            if(responseBody.success()){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
            this.writeStringToOutputStream(gson.toJson(responseBody), exchange.getResponseBody());

        }catch(Exception e){
             e.printStackTrace();
             exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }finally{
            exchange.getResponseBody().close();

        }
    
    }

}