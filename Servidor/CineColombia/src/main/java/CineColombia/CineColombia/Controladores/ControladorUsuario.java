package CineColombia.CineColombia.Controladores;

import CineColombia.CineColombia.Modelos.Usuario;
import CineColombia.CineColombia.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @Autowired
    private RepositorioUsuario miRepositorioUsuario;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody Usuario infoUsuario){
        return this.miRepositorioUsuario.save(infoUsuario);
    }
    @GetMapping("")
    public List<Usuario> index(){
        return this.miRepositorioUsuario.findAll();
    }
    @GetMapping("{id}")
    public Usuario show(@PathVariable String id){
        Usuario UsuarioActual=this.miRepositorioUsuario
                .findById(id)
                .orElseThrow(RuntimeException::new);
        return UsuarioActual;
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Usuario UsuarioActual=this.miRepositorioUsuario
                .findById(id)
                .orElseThrow(RuntimeException::new);
        this.miRepositorioUsuario.delete(UsuarioActual);
    }
    @PutMapping("{id}")
    public Usuario update(@PathVariable String id,@RequestBody  Usuario infoUsuario){
        Usuario UsuarioActual=this.miRepositorioUsuario
                .findById(id)
                .orElseThrow(RuntimeException::new);
        UsuarioActual.setNombre(infoUsuario.getNombre());
        UsuarioActual.setAñoNacimiento(infoUsuario.getAñoNacimiento());
        UsuarioActual.setCedula(infoUsuario.getCedula());
        UsuarioActual.setEmail(infoUsuario.getEmail());
        return this.miRepositorioUsuario.save(UsuarioActual);
    }



}
