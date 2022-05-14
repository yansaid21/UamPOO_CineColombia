package CineColombia.CineColombia.Controladores;

import CineColombia.CineColombia.Modelos.Sala;
import CineColombia.CineColombia.Repositorios.RepositorioSala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/salas")
public class ControladorSala {
    @Autowired
    private RepositorioSala miRepositorioSala;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Sala create(@RequestBody Sala infoSala){
        return this.miRepositorioSala.save(infoSala);
    }
    @GetMapping("")
    public List<Sala> index(){
        return this.miRepositorioSala.findAll();
    }
    @GetMapping("{id}")
    public Sala show(@PathVariable String id){
        Sala SalaActual=this.miRepositorioSala
                .findById(id)
                .orElseThrow(RuntimeException::new);
        return SalaActual;
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Sala SalaActual=this.miRepositorioSala
                .findById(id)
                .orElseThrow(RuntimeException::new);
        this.miRepositorioSala.delete(SalaActual);
    }
    @PutMapping("{id}")
    public Sala update(@PathVariable String id,@RequestBody  Sala infoSala){
        Sala SalaActual=this.miRepositorioSala
                .findById(id)
                .orElseThrow(RuntimeException::new);
        SalaActual.setNombre(infoSala.getNombre());
        SalaActual.setEfectosEspeciales(infoSala.isEfectosEspeciales());
        return this.miRepositorioSala.save(SalaActual);
    }



}
