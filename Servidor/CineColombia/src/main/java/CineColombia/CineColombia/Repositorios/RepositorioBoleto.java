package CineColombia.CineColombia.Repositorios;

import CineColombia.CineColombia.Modelos.Boleto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioBoleto extends MongoRepository<Boleto,String> {

}
