package CineColombia.CineColombia.Repositorios;

import CineColombia.CineColombia.Modelos.Pelicula;
import CineColombia.CineColombia.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepositorioPelicula extends MongoRepository<Pelicula,String> {
    @Query("{'nombre':?0}")
    public Pelicula getPelicula(String nombre);
}
