package it.uniroma3.MyRecipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.MyRecipe.model.Ricetta;
import it.uniroma3.MyRecipe.repository.RicettaRepository;


//classe per definire le operazioni CRUD tramite metodi java: descrive i servizi offerti
@Service
public class RicettaService {
	
	@Autowired private RicettaRepository ricettaRepository;	//istanza costruita e inizializzata dal framework
	
	public Iterable<Ricetta> getAllRecipes(){
		return this.ricettaRepository.findAll();
	}

    public void save(Ricetta recipe) {
        this.ricettaRepository.save(recipe);
    }
	
	public Ricetta getRecipeById(Long id) {
		return this.ricettaRepository.findById(id).get();
	}

//	
//	public void deleteRecipeById(Long id) {
//		this.recipeRepository.deleteById(id);
//	}
}