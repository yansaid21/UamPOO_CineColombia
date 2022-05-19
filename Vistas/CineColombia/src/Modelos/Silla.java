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
public class Silla {
    String _id;
    String letra;
    int numero;
    Boleto miBoleto;

    public Boleto getMiBoleto() {
        return miBoleto;
    }

    public void setMiBoleto(Boleto miBoleto) {
        this.miBoleto = miBoleto;
    }

    public Silla() {
    }

    public Silla( String letra, int numero) {
        this.letra = letra;
        this.numero = numero;
    }
    public JSONObject toJson() {
        JSONObject respuesta = new JSONObject();
        respuesta.put("letra", this.getLetra());
        respuesta.put("numero", this.getNumero());
        
        return respuesta;
    }

    public String getId() {
        return _id;
    }

    public String getLetra() {
        return letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    
}
