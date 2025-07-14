package it.uniroma3.MyRecipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.MyRecipe.model.Ingrediente;
import it.uniroma3.MyRecipe.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired private IngredienteRepository ingredienteRepository;	//istanza costruita e inizializzata dal framework
	
	public Iterable<Ingrediente> getAllIngredients(){
		return this.ingredienteRepository.findAll();
	}

    public void save(Ingrediente ingrediente) {
        this.ingredienteRepository.save(ingrediente);
    }
	
	public Ingrediente getIngredientById(Long id) {
		return this.ingredienteRepository.findById(id).get();
	}
}
