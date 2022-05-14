package CineColombia.CineColombia.Controladores;

import CineColombia.CineColombia.Modelos.Boleto;
import CineColombia.CineColombia.Repositorios.RepositorioBoleto;
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



}
