package it.uniroma3.MyRecipe.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.MyRecipe.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>{
}