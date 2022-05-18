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
public class Usuario {
    private String _id;
    private String cedula;
    private String nombre;
    private String email;
    private int anoNacimiento;
    
    private Boleto miBoleto;

    public Usuario() {
    }

    public Usuario( String cedula, String nombre, String email, int anoNacimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.anoNacimiento = anoNacimiento;
    }
    
    public JSONObject toJson() {
        JSONObject respuesta = new JSONObject();
        respuesta.put("cedula", this.getCedula());
        respuesta.put("nombre", this.getNombre());
        respuesta.put("email", this.getEmail());
        respuesta.put("anoNacimiento", this.getAnoNacimiento());
        return respuesta;
    }

    public String getId() {
        return _id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    /**
     * @return the miBoleto
     */
    public Boleto getMiBoleto() {
        return miBoleto;
    }

    /**
     * @param miBoleto the miBoleto to set
     */
    public void setMiBoleto(Boleto miBoleto) {
        this.miBoleto = miBoleto;
    }
    
    
}
