package it.uniroma3.MyRecipe.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.MyRecipe.model.Ingrediente;
import it.uniroma3.MyRecipe.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired private IngredienteRepository ingredienteRepository;	//istanza costruita e inizializzata dal framework
	
	//ritorna la lista di tutti gli ingredienti in ordine alfabetico
	public Iterable<Ingrediente> getAllIngredients(){
		
		Iterable<Ingrediente> ingredientiIt = this.ingredienteRepository.findAll();
		
		List<Ingrediente> listaIngredienti = new ArrayList<Ingrediente>();
		
		for(Ingrediente ingr : ingredientiIt)
			listaIngredienti.add(ingr);
		
	    listaIngredienti.sort(Comparator.comparing(Ingrediente::getNome));
	    
		return listaIngredienti;
	}

    public void save(Ingrediente ingrediente) {
        this.ingredienteRepository.save(ingrediente);
    }
	
	public Ingrediente getIngredientById(Long id) {
		return this.ingredienteRepository.findById(id).get();
	}
	
//	public Iterable<Ingrediente> findIngredientsById(List<Long> ids) {
//		return this.ingredienteRepository.findAllById(ids);
//	}
	
	//verifica che un ingrediente sia già presente nel DB
	public boolean existIngredientByName(String name) {
		Iterable<Ingrediente> ingredientiEsistenti = this.ingredienteRepository.findAll();
		
		for(Ingrediente ingr : ingredientiEsistenti) {
			if(ingr.getNome().equalsIgnoreCase(name))
				return true;
		}
	return false;	
	}
}
