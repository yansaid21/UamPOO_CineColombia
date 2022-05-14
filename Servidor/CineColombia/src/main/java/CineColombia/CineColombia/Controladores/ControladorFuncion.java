package CineColombia.CineColombia.Controladores;

import CineColombia.CineColombia.Modelos.Funcion;
import CineColombia.CineColombia.Repositorios.RepositorioFuncion;
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


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Funcion create(@RequestBody Funcion infoFuncion){
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
        FuncionActual.setAño(infoFuncion.getAño());
        FuncionActual.setDia(infoFuncion.getDia());
        FuncionActual.setHora(infoFuncion.getHora());
        FuncionActual.setMes(infoFuncion.getMes());
        return this.miRepositorioFuncion.save(FuncionActual);
    }



}
