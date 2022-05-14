/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Usuario;
import Servicios.Servicio;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Geraldine Romero
 */
public class ControladorUsuario {
    Servicio miServicio;
    String subUrl;

    public ControladorUsuario(Servicio miServicio, String subUrl) {
        this.miServicio = miServicio;
        this.subUrl = subUrl;
    }
    
    public Usuario armar(String jsonString){
        Usuario nuevoUsuario = new Usuario();
        try {
            JSONParser parser= new JSONParser();
            JSONObject usuarioJson = (JSONObject) parser.parse(jsonString);
            nuevoUsuario.setId((String)usuarioJson.get("_id"));
            nuevoUsuario.setCedula((String)usuarioJson.get("cedula"));
            nuevoUsuario.setNombre((String)usuarioJson.get("nombre"));
            nuevoUsuario.setEmail((String)usuarioJson.get("email"));
            nuevoUsuario.setAñoNacimiento((Integer)usuarioJson.get("añoNacimiento"));
        } catch (Exception e) {
            nuevoUsuario = null;
        }
        return nuevoUsuario;
    }
}
