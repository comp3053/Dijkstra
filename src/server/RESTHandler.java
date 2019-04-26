package server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Ingredient;
import sun.misc.IOUtils;

import java.io.*;

public class RESTHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String queryMethod = httpExchange.getRequestMethod();
        String queryURL = httpExchange.getRequestURI().toString();
        String rsp = "{}";
        if (queryMethod.equals("GET")) {
            switch (queryURL) {
                case "/ingredient":
                    IngredientController ic = new IngredientController();
                    rsp = ic.getIngredientList();
                    break;
                case "/equipment":
                    EquipmentController ec = new EquipmentController();
                    rsp = ec.getEquipmentInfo();
                    break;
                    default:
                        break;
            }
        }
        else if (queryMethod.equals("POST")) {
            switch (queryURL) {
                case "/ingredient":
                    InputStream is = httpExchange.getRequestBody();
                    char[] buffer = new char[1000];
                    Reader reader = new InputStreamReader(is, "UTF-8");
                    reader.read(buffer, 0, 1000);
                    System.out.println(buffer);
                    rsp = "{'msg': 'Success'}";
                    break;
                    default:
                        break;
            }
        }
        Headers responseHeader = httpExchange.getResponseHeaders();
        responseHeader.add("Access-Control-Allow-Origin", "*");
        responseHeader.add("Access-Control-Allow-Headers", "Content-Type");
        responseHeader.add("Content-Type", "application/json; charset=utf-8");
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(rsp.getBytes());
        os.close();
    }
}
