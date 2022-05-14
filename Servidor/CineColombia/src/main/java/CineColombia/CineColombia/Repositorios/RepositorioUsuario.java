package CineColombia.CineColombia.Repositorios;

import CineColombia.CineColombia.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepositorioUsuario extends MongoRepository<Usuario,String> {
    @Query("{'cedula':?0}")
    public Usuario getUsuario(String cedula);
}
