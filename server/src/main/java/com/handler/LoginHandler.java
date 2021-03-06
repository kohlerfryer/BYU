package com.familymap;

// import java.io.*;
// import java.net.*;
// import com.sun.net.httpserver.*;
import com.familymap.DBConnection;
import com.familymap.FamilyMapHandler;
import com.familymap.LoginResponseBody;
import com.familymap.LoginRequestBody;

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


public class LoginHandler extends FamilyMapHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().toLowerCase().equals("post"))return; 
        
        try{
            Gson gson = new Gson();
            String requestBodyString = this.getRequestBody(exchange);
            LoginRequestBody requestBody = gson.fromJson(requestBodyString, LoginRequestBody.class); 
            LoginService loginService = new LoginService();
            Headers reqestHeaders = exchange.getRequestHeaders();
            LoginResponseBody responseBody = loginService.login(requestBody);

            if(responseBody.wasSuccessfull()){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            }
            else{
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
            this.writeStringToOutputStream(gson.toJson(responseBody), exchange.getResponseBody());

        }catch(Exception e){
             e.printStackTrace();
             exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }
        exchange.getResponseBody().close();
    
    }

}