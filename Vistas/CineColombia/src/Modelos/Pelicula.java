/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.LinkedList;
import org.json.simple.JSONObject;

/**
 *
 * @author Geraldine Romero
 */
public class Pelicula {
    private String _id;
    private String nombre;
    private int año;
    private String tipo;

    private LinkedList <Funcion> misFunciones;
    
    public Pelicula() {
    }

    public Pelicula( String nombre, int año, String tipo) {
        this.nombre = nombre;
        this.año = año;
        this.tipo = tipo;
        this.misFunciones = new LinkedList<>();
    }

    public JSONObject toJson() {
        JSONObject respuesta = new JSONObject();
        respuesta.put("nombre", this.getNombre());
        respuesta.put("año", this.getAño());
        respuesta.put("tipo", this.getTipo());
        return respuesta;
    }
    
    public String getId() {
        return _id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAño() {
        return año;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the misFunciones
     */
    public LinkedList <Funcion> getMisFunciones() {
        return misFunciones;
    }

    /**
     * @param misFunciones the misFunciones to set
     */
    public void setMisFunciones(LinkedList <Funcion> misFunciones) {
        this.misFunciones = misFunciones;
    }
    
    
}
