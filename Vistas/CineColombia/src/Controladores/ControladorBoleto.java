/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Boleto;
import Servicios.Servicio;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Geraldine Romero
 */
public class ControladorBoleto {
    Servicio miServicio;
    String subUrl;

    public ControladorBoleto(String server, String subUrl) {
        this.miServicio = new Servicio(server);
        this.subUrl = subUrl;
    }
    
    public Boleto armar(String jsonString){
        Boleto nuevoBoleto = new Boleto();
        try {
            JSONParser parser = new JSONParser();
            JSONObject boletoJson= (JSONObject) parser.parse(jsonString);
            nuevoBoleto.setId((String)boletoJson.get("_id"));
            nuevoBoleto.setTipo((String)boletoJson.get("tipo"));
            nuevoBoleto.setValor((double) boletoJson.get("valor"));
        } catch (Exception e) {
            nuevoBoleto = null;
        }
        return nuevoBoleto;
    }
    
    public Boleto crear (Boleto nuevoBoleto){
        Boleto respuesta = new Boleto();
        try {
            String resultado = this.miServicio.POST(this.subUrl, nuevoBoleto.toJson());
            respuesta = armar(resultado);
        } catch (Exception e) {
            System.out.println("ERROR "+e);
            respuesta=null;
        }
        return respuesta;
    }
    
    public Boleto buscarPorId (String id){
        Boleto respuesta = new Boleto();
        try {
            String endPoint = this.subUrl+"/id/"+id;
            String resultado= this.miServicio.GET(endPoint);
            respuesta=armar(resultado);
        } catch (Exception e){
            System.out.println("ERROR "+e);
            respuesta=null;
        }
        return respuesta;
    }
    
    public Boleto actualizar (Boleto nuevoBoleto){
        Boleto respuesta = new Boleto();
        try {
            String resultado = this.miServicio.PUT(this.subUrl, nuevoBoleto.toJson());
            respuesta = armar(resultado);
        } catch (Exception e) {
            System.out.println("ERROR "+e);
            respuesta=null;
        }
        return respuesta;
    }
    
    public void eliminar (String id){
        String endPoint=this.subUrl+"/"+id;
        this.miServicio.DELETE(endPoint);
    }
}
