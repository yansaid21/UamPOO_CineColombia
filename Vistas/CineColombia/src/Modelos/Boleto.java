/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import org.json.simple.JSONObject;

/**
 *
 * @author Geraldine Romero
 */
public class Boleto {
    private String _id;
    private double valor;
    private String tipo;

    private Silla miSilla;
    
    public Boleto() {
    }

    public Boleto( double valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public JSONObject toJson() {
        JSONObject respuesta = new JSONObject();
        respuesta.put("valor", this.getValor());
        respuesta.put("tipo", this.getTipo());
        return respuesta;
    }
    
    /**
     * @return the _id
     */
    public String getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(String _id) {
        this._id = _id;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the miSilla
     */
    public Silla getMiSilla() {
        return miSilla;
    }

    /**
     * @param miSilla the miSilla to set
     */
    public void setMiSilla(Silla miSilla) {
        this.miSilla = miSilla;
    }
    
    
}
