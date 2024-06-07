package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Receitas;

public interface ReceitasRepository extends CrudRepository <Receitas, Long>{
    
}
