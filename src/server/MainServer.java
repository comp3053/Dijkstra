package server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MainServer {
    public static void main(String[] args) throws IOException {
        int port = 8888;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/ingredient", new RESTHandler());
        server.createContext("/equipment", new RESTHandler());
        server.start();
        System.out.println("Server is running on port " + port);
    }
}
