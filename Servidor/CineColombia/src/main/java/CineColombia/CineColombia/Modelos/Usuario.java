package CineColombia.CineColombia.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class Usuario {
    @Id
    String _id;
    String cedula;
    String nombre;
    String email;
    int añoNacimiento;

    public Usuario(String cedula, String nombre, String email, int añoNaciemto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.añoNacimiento = añoNaciemto;
    }

    public String get_id() {
        return _id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(int añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }
}
