package CineColombia.CineColombia.Modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class Boleto {
    @Id
    String _id;
    double valor;
    String tipo;

    public Boleto(double valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public String get_id() {
        return _id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
