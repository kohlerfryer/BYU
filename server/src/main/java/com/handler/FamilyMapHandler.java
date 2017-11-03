package com.familymap;

import java.io.PrintWriter;
import java.io.Scanner;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

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


    //Gson gson = new Gson();

// // 1. JSON to Java object, read it from a file.
// Staff staff = gson.fromJson(new FileReader("D:\\file.json"), Staff.class);

// // 2. JSON to Java object, read it from a Json String.
// String jsonInString = "{'name' : 'mkyong'}";
// Staff staff = gson.fromJson(jsonInString, Staff.class);

// // JSON to JsonElement, convert to String later.
// JsonElement json = gson.fromJson(new FileReader("D:\\file.json"), JsonElement.class);
//     String result = gson.toJson(json);
    

// Gson gson = new Gson();
// Staff obj = new Staff();

// // 1. Java object to JSON, and save into a file
// gson.toJson(obj, new FileWriter("D:\\file.json"));

// // 2. Java object to JSON, and assign to a String
// String jsonInString = gson.toJson(obj);


}