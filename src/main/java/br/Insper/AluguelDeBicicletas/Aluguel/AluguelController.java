package br.Insper.AluguelDeBicicletas.Aluguel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @PostMapping("/aluguel")
    public ResponseEntity<Aluguel> inicializarAluguel(@RequestBody Aluguel aluguel) {
        Aluguel novoAluguel = aluguelService.inicializarAluguel(aluguel);
        return new ResponseEntity<>(novoAluguel, HttpStatus.CREATED);
    }

    @PutMapping("/aluguel/{id}")
    public ResponseEntity<Aluguel> finalizarAluguel(@PathVariable Integer id, @RequestBody Aluguel infosFinais) {
        Aluguel aluguelFinalizado = aluguelService.finalizarAluguel(id, infosFinais);
        return new ResponseEntity<>(aluguelFinalizado, HttpStatus.OK);
    }

    @DeleteMapping("/aluguel/{id}")
    public ResponseEntity<Aluguel> excluirAluguel(@PathVariable Integer id) {
        Aluguel aluguelExcluido = aluguelService.excluirAluguel(id);
        return new ResponseEntity<>(aluguelExcluido, HttpStatus.OK);
    }

    @GetMapping("/aluguel")
    public ResponseEntity<List<Aluguel>> aluguelPorBicicleta(@RequestParam(required = false) Integer idBicicleta, @RequestParam(required = false)  String status) {
        List<Aluguel> aluguel;
        if (idBicicleta != null) {
            aluguel = aluguelService.aluguelPorBicicleta(idBicicleta);
        }
        else if (status != null) {
            aluguel = aluguelService.aluguelPorStatus(status);
        }
        else{
            aluguel = aluguelService.aluguelPorBicicleta(null);
        }
        return new ResponseEntity<>(aluguel, HttpStatus.OK);
    }

    @GetMapping("/aluguel/{id}")
    public ResponseEntity<Aluguel> aluguelPorId(@PathVariable Integer id){
        Aluguel aluguel = aluguelService.aluguelPorId(id);
        return new ResponseEntity<>(aluguel, HttpStatus.OK);
    }


}