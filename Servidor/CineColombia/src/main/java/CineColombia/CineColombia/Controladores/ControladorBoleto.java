package CineColombia.CineColombia.Controladores;

import CineColombia.CineColombia.Modelos.Boleto;
import CineColombia.CineColombia.Modelos.Funcion;
import CineColombia.CineColombia.Modelos.Silla;
import CineColombia.CineColombia.Modelos.Usuario;
import CineColombia.CineColombia.Repositorios.RepositorioBoleto;
import CineColombia.CineColombia.Repositorios.RepositorioFuncion;
import CineColombia.CineColombia.Repositorios.RepositorioSilla;
import CineColombia.CineColombia.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/boletos")
public class ControladorBoleto {
    @Autowired
    private RepositorioBoleto miRepositorioBoleto;
    @Autowired
    private RepositorioUsuario miRepositorioUsuario;
    @Autowired
    private RepositorioFuncion miRepositorioFuncion;
    @Autowired
    private RepositorioSilla miRepositorioSilla;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Boleto create(@RequestBody Boleto infoBoleto){
        return this.miRepositorioBoleto.save(infoBoleto);
    }
    @GetMapping("")
    public List<Boleto> index(){
        return this.miRepositorioBoleto.findAll();
    }
    @GetMapping("{id}")
    public Boleto show(@PathVariable String id){
        Boleto BoletoActual=this.miRepositorioBoleto
                .findById(id)
                .orElseThrow(RuntimeException::new);
        return BoletoActual;
    }
    /*@GetMapping("/referencia/{referencia}")
    public Boleto buscarPorReferencia(@PathVariable String referencia){
        Boleto boletoActual =this.miRepositorioBoleto.getReferencia(referencia);
        return boletoActual;
    }*/
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Boleto BoletoActual=this.miRepositorioBoleto
                .findById(id)
                .orElseThrow(RuntimeException::new);
        this.miRepositorioBoleto.delete(BoletoActual);
    }
    @PutMapping("{id}")
    public Boleto update(@PathVariable String id,@RequestBody  Boleto infoBoleto){
        Boleto BoletoActual=this.miRepositorioBoleto
                .findById(id)
                .orElseThrow(RuntimeException::new);
        BoletoActual.setValor(infoBoleto.getValor());
        BoletoActual.setTipo(infoBoleto.getTipo());
        return this.miRepositorioBoleto.save(BoletoActual);
    }
    @PutMapping("{id_boleto}/usuario/{id_usuario}")
    public Boleto updateUsuario(@PathVariable String id_boleto, @PathVariable  String id_usuario){
        Boleto boletoActual=this.miRepositorioBoleto
                .findById(id_boleto)
                .orElseThrow(RuntimeException::new);

        Usuario usuarioActual= this.miRepositorioUsuario
                .findById(id_usuario)
                .orElseThrow(RuntimeException::new);

        boletoActual.setUsuario(usuarioActual) ;
        return this.miRepositorioBoleto.save(boletoActual);
    }
    @PutMapping("{id_boleto}/funcion/{id_funcion}")
    public Boleto updateFuncion(@PathVariable String id_boleto, @PathVariable  String id_funcion){
        Boleto boletoActual=this.miRepositorioBoleto
                .findById(id_boleto)
                .orElseThrow(RuntimeException::new);

        Funcion funcionActual= this.miRepositorioFuncion
                .findById(id_funcion)
                .orElseThrow(RuntimeException::new);

        boletoActual.setFuncion(funcionActual); ;
        return this.miRepositorioBoleto.save(boletoActual);
    }
    @PutMapping("{id_boleto}/silla/{id_silla}")
    public Boleto updateSilla(@PathVariable String id_boleto, @PathVariable  String id_silla){
        Boleto boletoActual=this.miRepositorioBoleto
                .findById(id_boleto)
                .orElseThrow(RuntimeException::new);

        Silla sillaActual = this.miRepositorioSilla
                .findById(id_silla)
                .orElseThrow(RuntimeException::new);

        boletoActual.setSilla(sillaActual); ;
        return this.miRepositorioBoleto.save(boletoActual);
    }

}
