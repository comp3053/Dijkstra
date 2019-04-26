package server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MainServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
        server.createContext("/ingredient", new RESTHandler());
        server.createContext("/equipment", new RESTHandler());
        server.start();
    }
}
