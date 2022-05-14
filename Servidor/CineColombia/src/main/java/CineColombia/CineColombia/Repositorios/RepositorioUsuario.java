package CineColombia.CineColombia.Repositorios;

import CineColombia.CineColombia.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuario extends MongoRepository<Usuario,String> {
}
