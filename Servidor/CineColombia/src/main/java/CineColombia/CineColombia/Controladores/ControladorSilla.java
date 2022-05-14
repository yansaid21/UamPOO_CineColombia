package CineColombia.CineColombia.Controladores;

import CineColombia.CineColombia.Modelos.Silla;
import CineColombia.CineColombia.Repositorios.RepositorioSilla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sillas")
public class ControladorSilla {
    @Autowired
    private RepositorioSilla miRepositorioSilla;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Silla create(@RequestBody Silla infoSilla){
        return this.miRepositorioSilla.save(infoSilla);
    }
    @GetMapping("")
    public List<Silla> index(){
        return this.miRepositorioSilla.findAll();
    }
    @GetMapping("{id}")
    public Silla show(@PathVariable String id){
        Silla SillaActual=this.miRepositorioSilla
                .findById(id)
                .orElseThrow(RuntimeException::new);
        return SillaActual;
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Silla SillaActual=this.miRepositorioSilla
                .findById(id)
                .orElseThrow(RuntimeException::new);
        this.miRepositorioSilla.delete(SillaActual);
    }
    @PutMapping("{id}")
    public Silla update(@PathVariable String id,@RequestBody  Silla infoSilla){
        Silla SillaActual=this.miRepositorioSilla
                .findById(id)
                .orElseThrow(RuntimeException::new);
        SillaActual.setLetra(infoSilla.getLetra());
        SillaActual.setNumero(infoSilla.getNumero());
        return this.miRepositorioSilla.save(SillaActual);
    }



}
