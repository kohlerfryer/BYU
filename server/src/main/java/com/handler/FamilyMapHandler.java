package com.familymap;

public class FamilyMapHandler{
    
    // String getRequestBody(HttpExchange exchange) {
    //     System.out.println("request body:");
    //     String request = "";
        
    //     Scanner in = new Scanner(exchange.getRequestBody());
    //     while (in.hasNextLine()) {
    //         String line = in.nextLine();
    //         request += line + "\n";
    //     }

    //     in.close();
    //     return request;
    // }

    // void sendResponseBody(HttpExchange exchange, String response) {
    //     PrintWriter out = new PrintWriter(exchange.getResponseBody());
    //     out.print(response);
    //     out.close();
    // }

    // boolean hasValidAuthToken(HttpExchange exchange) {
    //     System.out.println("request:");

    //     String method = exchange.getRequestMethod();
    //     System.out.println("method: " + method);
                
    //     URI uri = exchange.getRequestURI();
    //     System.out.println("uri: " + uri);
                
    //     String auth = exchange.getRequestHeaders().getFirst("Authorization");
    //     System.out.println("auth: " + auth);

    //     System.out.println();

    //     //printHeaders(exchange.getRequestHeaders());
    // }

}