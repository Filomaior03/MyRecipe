package it.uniroma3.MyRecipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.MyRecipe.model.Credenziali;

public interface CredenzialiRepository extends CrudRepository<Credenziali, Long>{
	
	public Optional<Credenziali> findByUsername(String username);

}
