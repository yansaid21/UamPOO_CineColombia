/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Boleto;
import Modelos.Funcion;
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

    public ControladorFuncion(Servicio miServicio, String subUrl) {
        this.miServicio = miServicio;
        this.subUrl = subUrl;
    }
    
    public Funcion armar(String jsonString){
        Funcion nuevaFuncion = new Funcion();
        try {
            JSONParser parser = new JSONParser();
            JSONObject funcionJson= (JSONObject) parser.parse(jsonString);
            nuevaFuncion.setId((String)funcionJson.get("_id"));
            nuevaFuncion.setHora((Integer)funcionJson.get("hora"));
            nuevaFuncion.setDia((Integer)funcionJson.get("dia"));
            nuevaFuncion.setAño((Integer)funcionJson.get("año"));
            nuevaFuncion.setMes((Integer)funcionJson.get("mes"));
        } catch (Exception e) {
            nuevaFuncion = null;
        }
        return nuevaFuncion;
    }
    
    public Funcion crear (Funcion nuevaFuncion){
        Funcion respuesta = new Funcion();
        try {
            String resultado = this.miServicio.POST(this.subUrl, nuevaFuncion.toJson());
            respuesta = armar(resultado);
        } catch (Exception e) {
            System.out.println("ERROR "+e);
            respuesta=null;
        }
        return respuesta;
    }
}
