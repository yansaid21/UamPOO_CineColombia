package CineColombia.CineColombia.Repositorios;

import CineColombia.CineColombia.Modelos.Silla;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RepositorioSilla extends MongoRepository<Silla,String> {
    @Query("{'Sala.$id': ObjectId(?0)}")
    List<Silla> getSillasEnSala(String idSala);
}
