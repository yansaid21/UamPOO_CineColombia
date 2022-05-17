package CineColombia.CineColombia.Controladores;

import CineColombia.CineColombia.Modelos.Pelicula;
import CineColombia.CineColombia.Repositorios.RepositorioPelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/peliculas")
public class ControladorPelicula {
    @Autowired
    private RepositorioPelicula miRepositorioPelicula;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Pelicula create(@RequestBody Pelicula infoPelicula){
        return this.miRepositorioPelicula.save(infoPelicula);
    }
    @GetMapping("")
    public List<Pelicula> index(){
        return this.miRepositorioPelicula.findAll();
    }
    @GetMapping("{id}")
    public Pelicula show(@PathVariable String id){
        Pelicula PeliculaActual=this.miRepositorioPelicula
                .findById(id)
                .orElseThrow(RuntimeException::new);
        return PeliculaActual;
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Pelicula PeliculaActual=this.miRepositorioPelicula
                .findById(id)
                .orElseThrow(RuntimeException::new);
        this.miRepositorioPelicula.delete(PeliculaActual);
    }
    @PutMapping("{id}")
    public Pelicula update(@PathVariable String id,@RequestBody  Pelicula infoPelicula){
        Pelicula PeliculaActual=this.miRepositorioPelicula
                .findById(id)
                .orElseThrow(RuntimeException::new);
        PeliculaActual.setNombre(infoPelicula.getNombre());
        PeliculaActual.setAno(infoPelicula.getAno());
        PeliculaActual.setTipo(infoPelicula.getTipo());
        return this.miRepositorioPelicula.save(PeliculaActual);
    }



}
