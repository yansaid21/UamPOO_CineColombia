package CineColombia.CineColombia.Repositorios;

import CineColombia.CineColombia.Modelos.Silla;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioSilla extends MongoRepository<Silla,String> {
}
