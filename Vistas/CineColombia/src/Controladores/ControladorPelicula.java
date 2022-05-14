/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Pelicula;
import Servicios.Servicio;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Geraldine Romero
 */
public class ControladorPelicula {
    Servicio miServicio;
    String subUrl;

    public ControladorPelicula(Servicio miServicio, String subUrl) {
        this.miServicio = miServicio;
        this.subUrl = subUrl;
    }
    
    public Pelicula armar (String jsonString){
        Pelicula nuevaPelicula = new Pelicula();
        try {
            JSONParser parser = new JSONParser();
            JSONObject peliculaJson = (JSONObject) parser.parse(jsonString);
            nuevaPelicula.setId((String)peliculaJson.get("_id"));
            nuevaPelicula.setNombre((String)peliculaJson.get("nombre"));
            nuevaPelicula.setAño((Integer)peliculaJson.get("año"));
            nuevaPelicula.setTipo((String)peliculaJson.get("tipo"));
        } catch (Exception e) {
            nuevaPelicula = null;
        }
        return nuevaPelicula;
    }
    
}
