package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Ingredientes;

public interface IngredientesRepository extends CrudRepository <Ingredientes, Long>{
    
}
