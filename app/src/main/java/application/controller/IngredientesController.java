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

import application.model.Ingredientes;
import application.repository.IngredientesRepository;

@RestController
@RequestMapping("/ingredientes")
public class IngredientesController {
    @Autowired
    private IngredientesRepository ingredientesRepo;

    @GetMapping
    public Iterable<Ingredientes>getAll(){
        return ingredientesRepo.findAll();

    }
    @GetMapping("/{id}")
    public Ingredientes getOne(@PathVariable long id) {
        Optional <Ingredientes> result = ingredientesRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Ingrediente Não Encontrado"
            );
        }
        return result.get();
    }

    @PostMapping
    private Ingredientes post(@RequestBody Ingredientes ingredientes) {
        return ingredientesRepo.save(ingredientes);
    }

    @PutMapping("/{id}")
    private Ingredientes put(@RequestBody Ingredientes ingredientes, @PathVariable long id) {
        Optional<Ingredientes> result = ingredientesRepo.findById(id);

        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Ingrediente Não Encontrado"
            );
        }

        result.get().setNome(ingredientes.getNome());
        return ingredientesRepo.save(result.get());
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable long id) {
        if(ingredientesRepo.existsById(id)) {
            ingredientesRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Ingrediente Não Encontrado"
            );
        }
    }
}


