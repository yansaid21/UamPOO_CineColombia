package CineColombia.CineColombia.Repositorios;

import CineColombia.CineColombia.Modelos.Boleto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepositorioBoleto extends MongoRepository<Boleto,String> {
    /*@Query("{'referencia':?0}")
    public Boleto getBoleto(String referencia);*/
}
