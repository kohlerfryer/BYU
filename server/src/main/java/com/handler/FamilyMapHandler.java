package com.familymap;

import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

public class FamilyMapHandler{

	protected void writeStringToOutputStream(String string, OutputStream outputStream) throws IOException {
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
		outputStreamWriter.write(string);
		outputStreamWriter.flush();
	}

    protected String getRequestBody(HttpExchange exchange){
        String request = "";
        Scanner in = new Scanner(exchange.getRequestBody());
        while (in.hasNextLine()) {
            String line = in.nextLine();
            request += line + "\n";
        }
        in.close();
        return request;
    }

    protected void sendResponseBody(HttpExchange exchange, String response) {
        PrintWriter out = new PrintWriter(exchange.getResponseBody());
        out.print(response);
        out.close();
    }

    protected void validateAuthenticationToken(String token){
        DBConnection dbConnection = DBSingleton.getInstance();
        AuthenticationAccess authenticationAccess = new AuthenticationAccess(this.dbConnection);
        authenticationAccess.get("token", "=", token);
    }


}