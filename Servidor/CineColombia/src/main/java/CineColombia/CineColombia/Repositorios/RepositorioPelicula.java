package CineColombia.CineColombia.Repositorios;

import CineColombia.CineColombia.Modelos.Pelicula;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPelicula extends MongoRepository<Pelicula,String> {
}
