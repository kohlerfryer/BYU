package com.familymap;

public class ResponseBodyWrapper(){
    private boolean successResponse;
    private JsonObject responseBody;
    
    public ResponseBodyWrapper(boolean successResponse, JsonObject responseBody){
        this.successResponse = successResponse;
        this.responseBody = responseBody;
    }

    public String getResponseBody(){
        return responseBody.toString();
    }
    public boolean responseTypeSuccessfull(){
        return this.successResponse;
    }
    public JsonObject getResponseBody(){
        return this.responseBody;
    }
}

public class RegisterHandler extends FamilyMapHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().toLowerCase().equals("post"))return; 
    
        try{
            Gson gson = new Gson();
            String requestBody = this.getRequestBody(exchange);
            JsonObject registerRequestBody = parser.parse(requestBody).getAsJsonObject();

            RegisterService registerService = new RegisterService(dbConnection);
            Headers reqestHeaders = exchange.getRequestHeaders();
            JsonParser parser = new JsonParser();


            ResponseBodyWrapper responseBodyWrapper = registerService.register(registerRequestBody);
            this.writeStringToOutputStream(responseBodyWrapper.getResponseBody(), exchange.getResponseBody());

            if(responseBody instanceof errorResponseBody){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
            else if(responseBody instanceof successResponseBody){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            }

        }catch(Exception e){
             exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }
        exchange.getResponseBody().close();
    
    }

}