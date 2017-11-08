package com.familymap;

import com.familymap.RegisterHandler;
import com.familymap.IndexHandler;
import com.familymap.ClearHandler;
import com.familymap.LoginHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
public class Server {

    public static void main(String[] args) throws Exception {
        int portNumber = Integer.valueOf(args[0]);
        HttpServer server = HttpServer.create(new InetSocketAddress(portNumber), 50);
        //HttpServer server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNumber)), MAX_WAITING_CONNECTIONS);
        initializeHanlders(server);
        server.setExecutor(null);
        server.start();
    }

    private static void initializeHanlders(HttpServer server){
        server.createContext("/", new IndexHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear",  new ClearHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/person", new PersonHandler());        
        server.createContext("/event", new EventHandler());
    }

}


