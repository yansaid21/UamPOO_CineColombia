package CineColombia.CineColombia.Controladores;

import CineColombia.CineColombia.Modelos.Funcion;
import CineColombia.CineColombia.Modelos.Pelicula;
import CineColombia.CineColombia.Modelos.Sala;
import CineColombia.CineColombia.Repositorios.RepositorioFuncion;
import CineColombia.CineColombia.Repositorios.RepositorioPelicula;
import CineColombia.CineColombia.Repositorios.RepositorioSala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/funciones")
public class ControladorFuncion {
    @Autowired
    private RepositorioFuncion miRepositorioFuncion;
    @Autowired
    private RepositorioSala miRepositorioSala;
    @Autowired
    private RepositorioPelicula miRepositorioPelicula;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sala/{id_sala}/pelicula/{id_pelicula}")
    public Funcion create(@RequestBody Funcion infoFuncion,
                            @PathVariable String id_sala,
                            @PathVariable String id_pelicula){

        Sala salaActual= this.miRepositorioSala
                .findById(id_sala)
                .orElseThrow(RuntimeException::new);

        Pelicula peliculaActual=this.miRepositorioPelicula
                .findById(id_pelicula)
                .orElseThrow(RuntimeException::new);
        infoFuncion.setSala(salaActual);
        infoFuncion.setPelicula(peliculaActual);
        return this.miRepositorioFuncion.save(infoFuncion);
    }
    @GetMapping("")
    public List<Funcion> index(){
        return this.miRepositorioFuncion.findAll();
    }
    @GetMapping("{id}")
    public Funcion show(@PathVariable String id){
        Funcion FuncionActual=this.miRepositorioFuncion
                .findById(id)
                .orElseThrow(RuntimeException::new);
        return FuncionActual;
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Funcion FuncionActual=this.miRepositorioFuncion
                .findById(id)
                .orElseThrow(RuntimeException::new);
        this.miRepositorioFuncion.delete(FuncionActual);
    }
    @PutMapping("{id}")
    public Funcion update(@PathVariable String id,@RequestBody  Funcion infoFuncion){
        Funcion FuncionActual=this.miRepositorioFuncion
                .findById(id)
                .orElseThrow(RuntimeException::new);
        FuncionActual.setAno(infoFuncion.getAno());
        FuncionActual.setDia(infoFuncion.getDia());
        FuncionActual.setHora(infoFuncion.getHora());
        FuncionActual.setMes(infoFuncion.getMes());
        return this.miRepositorioFuncion.save(FuncionActual);
    }
    @PutMapping("{id}/sala/{id_sala}/pelicula/{id_pelicula}")
    public Funcion update(@PathVariable String id,@RequestBody  Funcion infoFuncion,@PathVariable String id_sala, @PathVariable String id_pelicula){
        Funcion FuncionActual=this.miRepositorioFuncion
                .findById(id)
                .orElseThrow(RuntimeException::new);
        FuncionActual.setAno(infoFuncion.getAno());
        FuncionActual.setDia(infoFuncion.getDia());
        FuncionActual.setHora(infoFuncion.getHora());
        FuncionActual.setMes(infoFuncion.getMes());
        Sala salaActual= this.miRepositorioSala
                .findById(id_sala)
                .orElseThrow(RuntimeException::new);

        Pelicula peliculaActual=this.miRepositorioPelicula
                .findById(id_pelicula)
                .orElseThrow(RuntimeException::new);
        infoFuncion.setSala(salaActual);
        infoFuncion.setPelicula(peliculaActual);

        return this.miRepositorioFuncion.save(FuncionActual);
    }



}
