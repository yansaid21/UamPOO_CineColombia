package CineColombia.CineColombia.Repositorios;

import CineColombia.CineColombia.Modelos.Sala;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioSala extends MongoRepository<Sala,String> {
}
