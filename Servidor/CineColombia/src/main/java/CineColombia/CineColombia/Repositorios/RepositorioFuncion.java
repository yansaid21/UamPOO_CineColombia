package CineColombia.CineColombia.Repositorios;

import CineColombia.CineColombia.Modelos.Funcion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioFuncion extends MongoRepository<Funcion,String> {
}
