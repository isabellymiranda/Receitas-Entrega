package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Receitas;
import application.repository.ReceitasRepository;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {
    @Autowired
    private ReceitasRepository receitasRepo;

    @GetMapping
    public Iterable<Receitas>getAll(){
        return receitasRepo.findAll();

    }
    @GetMapping("/{id}")
    public Receitas getOne(@PathVariable long id) {
        Optional<Receitas> result = receitasRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Receitas Não Encontrada"
            );
        }
        return result.get();
    }

    @PostMapping
    private Receitas post(@RequestBody Receitas receitas) {
        return receitasRepo.save(receitas);
    }
    
    @PutMapping("/{id}")
    private Receitas put(@RequestBody Receitas receitas, @PathVariable long id) {
        Optional<Receitas> result = receitasRepo.findById(id);

        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Receitas Não Encontrada"
            );
        }

        result.get().setNome(receitas.getNome());
        result.get().setInstrucao(receitas.getInstrucao());
        return receitasRepo.save(result.get());
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable long id) {
        if(receitasRepo.existsById(id)) {
            receitasRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Receitas Não Encontrada"
            );
        }
    }
}


