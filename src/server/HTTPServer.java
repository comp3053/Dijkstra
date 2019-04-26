package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.alibaba.fastjson.*;


public class HTTPServer {

    private String[] getURLInfo(String requestHeader) {
        int begin = requestHeader.indexOf("/");
        int end = requestHeader.indexOf("?") - 1;
        return requestHeader.substring(begin, end).split("/");
    }

    private String[] getURLParam(String requestHeader) {
        int begin = requestHeader.indexOf("/?") + 2;
        int end = requestHeader.indexOf("HTTP/");
        String condition = requestHeader.substring(begin, end);
        return condition.trim().split("&");
    }

    private String getDataHandler(String[] url, String[] params) {
        switch (url[1]) {
            case "ingredient":
                IngredientController ic = new IngredientController();
                return ic.getIngredientList();
            case "equipment":
                EquipmentController ec = new EquipmentController();
                return ec.getEquipmentInfo();
        }
        return "{}";
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
                    if (requestHeader.startsWith("GET") || requestHeader.startsWith("POST")) {
                        String[] url = getURLInfo(requestHeader);
                        String[] param = getURLParam(requestHeader);
                        if (requestHeader.startsWith("GET")) {
                            rsp = getDataHandler(url, param);
                        }
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
                pw.println("Access-Control-Allow-Origin: *");
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
        HTTPServer server = new HTTPServer(8888);
    }
}
