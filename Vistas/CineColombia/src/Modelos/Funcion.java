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
public class Funcion {
    private String _id;
    private int hora;
    private int dia;
    private int mes;
    private int ano;

    private Pelicula miPelicula;
    private Sala miSala;
    private LinkedList <Boleto> misBoletos;
    
    public Funcion() {
    }

    public Funcion( int hora, int dia, int mes, int año) {
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
        this.ano = año;
        this.misBoletos = new LinkedList<>();
    }
    
    public JSONObject toJson() {
        JSONObject respuesta = new JSONObject();
        respuesta.put("hora", this.getHora());
        respuesta.put("dia", this.getDia());
        respuesta.put("mes", this.getMes());
        respuesta.put("año", this.getAño());
        return respuesta;
    }

    public String getId() {
        return _id;
    }

    public int getHora() {
        return hora;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAño() {
        return ano;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAño(int año) {
        this.ano = año;
    }

    /**
     * @return the miPelicula
     */
    public Pelicula getMiPelicula() {
        return miPelicula;
    }

    /**
     * @param miPelicula the miPelicula to set
     */
    public void setMiPelicula(Pelicula miPelicula) {
        this.miPelicula = miPelicula;
    }

    /**
     * @return the miSala
     */
    public Sala getMiSala() {
        return miSala;
    }

    /**
     * @param miSala the miSala to set
     */
    public void setMiSala(Sala miSala) {
        this.miSala = miSala;
    }

    /**
     * @return the misBoletos
     */
    public LinkedList <Boleto> getMisBoletos() {
        return misBoletos;
    }

    /**
     * @param misBoletos the misBoletos to set
     */
    public void setMisBoletos(LinkedList <Boleto> misBoletos) {
        this.misBoletos = misBoletos;
    }
    
    
}
