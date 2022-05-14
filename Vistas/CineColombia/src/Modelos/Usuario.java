/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Geraldine Romero
 */
public class Usuario {
    private String _id;
    private String cedula;
    private String nombre;
    private String email;
    private int añoNacimiento;
    
    private Boleto miBoleto;

    public Usuario() {
    }

    public Usuario( String cedula, String nombre, String email, int añoNacimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.añoNacimiento = añoNacimiento;
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

    public int getAñoNacimiento() {
        return añoNacimiento;
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

    public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
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
