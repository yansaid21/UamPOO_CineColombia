/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Geraldine Romero
 */
public class Servicio {
    String server = "";
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public Servicio(String server) {
        this.server = server;
    }

    public String GET(String endPoint) {
        String respuesta = "";
        System.out.println("Solicitado a "+this.server + endPoint);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.server + endPoint))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            respuesta = response.body();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex);
        }
        return respuesta;
    }

    public String POST(String endPoint,JSONObject data) {
        String respuesta="";
        System.out.println("Solicitado a "+this.server + endPoint);
        try {
            String postEndpoint = this.server + endPoint;
            String inputJson = data.toJSONString();
            System.out.println("data "+inputJson);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(postEndpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            respuesta=(String)response.body();
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
        return respuesta;
    }

    public String PUT(String endPoint,JSONObject data) {
        String respuesta="";
        System.out.println("Solicitado a "+this.server + endPoint);
        try {
            String postEndpoint = this.server + endPoint;
            String inputJson = data.toJSONString();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(postEndpoint))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(inputJson))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            respuesta=(String)response.body();
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
        return respuesta;
    }
    public String DELETE(String endPoint) {
        String respuesta="";
        System.out.println("Solicitado a "+this.server + endPoint);
        try {
           String deleteEndpoint = this.server+endPoint;
 
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(deleteEndpoint))
            .header("Content-Type", "application/json")
            .DELETE()
            .build();
 
        HttpClient client = HttpClient.newHttpClient();
 
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
        System.out.println(response.statusCode());
        System.out.println(response.body());
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
        return respuesta;
    }
}
