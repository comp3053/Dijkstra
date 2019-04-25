package server;

import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.alibaba.fastjson.*;

public class HTTPServer {

    private String dataToJson(String str) {
        String[] data = str.trim().split("&");
        JSONObject obj = new JSONObject();
        for (String s : data) {
            String[] keyValue = s.split("=");
            try {
                obj.put(keyValue[0], keyValue[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("ERR: " + s);
                System.err.println("ERR: " + str);
            }
        }
        return obj.toString();
    }

    public HTTPServer(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);

            while (true) {
                Socket socket = ss.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String requestHeader;
                String rsp = "{}";

                while ((requestHeader = reader.readLine()) != null && !requestHeader.isEmpty()) {
                    System.out.println(requestHeader);
                    if (requestHeader.startsWith("GET")) {
                        int begin = requestHeader.indexOf("/?") + 2;
                        int end = requestHeader.indexOf("HTTP/");
                        String condition = requestHeader.substring(begin, end);
                        System.out.println(condition);
                        rsp = dataToJson(condition);
                    }
                }
//                StringBuffer sb = new StringBuffer();
//                if (contentLength > 0) {
//                    for (int i = 0; i < contentLength; i++) {
//                        sb.append((char) reader.read());
//                    }
//                    System.out.println("POST参数是：" + sb.toString());
//                }
                // Response
                PrintWriter pw = new PrintWriter(socket.getOutputStream());

                pw.println("HTTP/1.1 200 OK");
                pw.println("Content-type:application/json; charset=utf-8");
                pw.println();
                pw.println(rsp);

                pw.flush();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String[] test = "shit=1&bitch=fuck".split("&");
        System.out.println(test[0]);
        HTTPServer server = new HTTPServer(8888);
    }
}
