package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

public class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();

        URI uri = exchange.getRequestURI();
        System.out.println(uri);

        String response1 = Cantina.getInstance().getCantinaData(uri.toString().replace("/",""));

        String s = read(is);
        System.out.println(s);

        exchange.sendResponseHeaders(200, response1.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response1.getBytes());
        os.close();
    }

    private String read(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println("\n");
        StringBuilder received = new StringBuilder();
        while (true) {
            String s = "";
            try {
                if ((s = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            received.append(s);
        }
        return received.toString();
    }


}

