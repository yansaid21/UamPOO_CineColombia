/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Boleto;
import Modelos.Funcion;
import Modelos.Pelicula;
import Modelos.Sala;
import Servicios.Servicio;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Geraldine Romero
 */
public class ControladorFuncion {
    Servicio miServicio;
    String subUrl;

    public ControladorFuncion(String server, String subUrl) {
        this.miServicio = new Servicio(server) ;
        this.subUrl = subUrl;
    }
    
    public Funcion armar(String jsonString,Sala miSala,Pelicula miPelicula){
        Funcion nuevaFuncion = new Funcion();
        try {
            JSONParser parser = new JSONParser();
            JSONObject funcionJson= (JSONObject) parser.parse(jsonString);
            nuevaFuncion.setId((String)funcionJson.get("_id"));
            nuevaFuncion.setHora(((Long)funcionJson.get("hora")).intValue());
            nuevaFuncion.setDia(((Long)funcionJson.get("dia")).intValue());
            nuevaFuncion.setAno(((Long)funcionJson.get("ano")).intValue());
            nuevaFuncion.setMes(((Long)funcionJson.get("mes")).intValue());
            nuevaFuncion.setMiSala(miSala);
            nuevaFuncion.setMiPelicula(miPelicula);
        } catch (Exception e) {
            System.out.println(e);
            nuevaFuncion = null;
        }
        return nuevaFuncion;
    }
    
    public Funcion crear (Funcion nuevaFuncion,Sala miSala,Pelicula miPelicula){
        Funcion respuesta = new Funcion();
        try {
            String resultado = this.miServicio.POST(this.subUrl, nuevaFuncion.toJson());
            respuesta = armar(resultado,miSala,miPelicula);
        } catch (Exception e) {
            System.out.println("ERROR "+e);
            respuesta=null;
        }
        return respuesta;
    }
}
