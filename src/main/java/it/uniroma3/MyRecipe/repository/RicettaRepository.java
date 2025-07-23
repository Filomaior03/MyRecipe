package it.uniroma3.MyRecipe.repository;


import org.springframework.data.repository.CrudRepository;
import it.uniroma3.MyRecipe.model.Ricetta;

//classe per le operazioni della persistenza (CRUD: CREATE, READ, UPDATE, DELETE)
public interface RicettaRepository extends CrudRepository<Ricetta, Long>{

}