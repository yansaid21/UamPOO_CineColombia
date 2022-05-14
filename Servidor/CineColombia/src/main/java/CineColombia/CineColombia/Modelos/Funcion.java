package CineColombia.CineColombia.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class Funcion {
    @Id
    String _id;
    int hora;
    int dia;
    int mes;
    int año;

    public Funcion(int hora, int dia, int mes, int año) {
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String get_id() {
        return _id;
    }
}
