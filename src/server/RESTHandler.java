package server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.Ingredient;

import java.io.*;

public class RESTHandler implements HttpHandler {
    private String readInputStream(InputStream str) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(str, "UTF-8"));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        reader.close();
        return sb.toString();
    }

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
            String request = readInputStream(httpExchange.getRequestBody());
            switch (queryURL) {
                case "/ingredient":
                    IngredientController ic = new IngredientController();
                    rsp = ic.addIngredient(request);
                    break;
                    default:
                        break;
            }
        }
        else if (queryMethod.equals("PUT")) {
            String request = readInputStream(httpExchange.getRequestBody());
            switch (queryURL) {
                case "/ingredient":
                    IngredientController ic = new IngredientController();
                    rsp = ic.updateIngredient(request);
                    break;
                case "/equipment":
                    EquipmentController ec = new EquipmentController();
                    rsp = ec.updateEquipmentInfo(request);
                    break;
                    default:
                        break;
            }
        }
        else if (queryMethod.equals("DELETE")) {
            String path = httpExchange.getRequestURI().getPath();
            String request = httpExchange.getRequestURI().getQuery();
            switch (path) {
                case "/ingredient":
                    IngredientController ic = new IngredientController();
                    rsp = ic.deleteIngredient(request);
                    break;
                default:
                    break;
            }
        }

        Headers responseHeader = httpExchange.getResponseHeaders();
        responseHeader.add("Access-Control-Allow-Origin", "*");
        responseHeader.add("Access-Control-Allow-Headers", "Content-Type");
        responseHeader.add("Content-Type", "application/json; charset=utf-8");
        responseHeader.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(rsp.getBytes());
        os.close();
    }
}
