package com.familymap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;


public class IndexHandler extends FamilyMapHandler implements HttpHandler{

    final private String webBaseDirectory = "www";
    final private String notFoundPage = "404.html";

	@Override
	public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().toLowerCase().equals("get"))return; 
        String pathInfo = exchage.getPathInfo();
        String[] pathVariables = pathInfo.split("\\/", -1);
        String seperator = System.getProperty("file.separator");
        URI uri = exchange.getRequestURI();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

        String filePathString;
        System.out.println(String.valueOf(uri));
        if(String.valueOf(uri).equals("/")){
            filePathString = "www/index.html";
        }else{
            filePathString = this.webBaseDirectory + String.valueOf(uri);
        }
    
       
        try{
            System.out.println(filePathString);
            Path filePath = FileSystems.getDefault().getPath(filePathString);
            Files.copy(filePath, exchange.getResponseBody());
        }catch (NoSuchFileException e){
            Path filePath = FileSystems.getDefault().getPath(this.webBaseDirectory + this.notFoundPage);
            Files.copy(filePath, exchange.getResponseBody());
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            exchange.getResponseBody().close();
        }
    
    }

}