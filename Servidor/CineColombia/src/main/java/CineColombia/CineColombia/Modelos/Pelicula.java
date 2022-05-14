package CineColombia.CineColombia.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class Pelicula {
    @Id
    String _id;
    String nombre;
    int año;
    String tipo;

    public Pelicula(String nombre, int año, String tipo) {
        this.nombre = nombre;
        this.año = año;
        this.tipo = tipo;
    }

    public String get_id() {
        return _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
