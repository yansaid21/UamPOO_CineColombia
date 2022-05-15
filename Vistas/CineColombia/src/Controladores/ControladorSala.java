/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Funcion;
import Modelos.Sala;
import Servicios.Servicio;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Geraldine Romero
 */
public class ControladorSala {
    Servicio miServicio;
    String subUrl;

    public ControladorSala(String server, String subUrl) {
        this.miServicio = new Servicio(server);
        this.subUrl = subUrl;
    }

    public Sala armar(String jsonString){
        Sala nuevaSala = new Sala();
        try {
            JSONParser parser = new JSONParser();
            JSONObject salaJson= (JSONObject) parser.parse(jsonString);
            nuevaSala.setId((String)salaJson.get("_id"));
            nuevaSala.setNombre((String)salaJson.get("nombre"));
            nuevaSala.setEfectosEspeciales((boolean) salaJson.get("efectosEspeciales"));
        } catch (Exception e) {
            nuevaSala = null;
        }
        return nuevaSala;
    }

    public Sala crear(Sala sala1) {
        Sala respuesta = new Sala();
        try {
            String resultado = this.miServicio.POST(this.subUrl, sala1.toJson());
            respuesta = armar(resultado);
        } catch (Exception e) {
            System.out.println("ERROR "+e);
            respuesta=null;
        }
        return respuesta;
    }
}
