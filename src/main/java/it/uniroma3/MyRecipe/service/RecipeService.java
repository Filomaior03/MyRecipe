package it.uniroma3.MyRecipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.MyRecipe.model.Recipe;
import it.uniroma3.MyRecipe.repository.RecipeRepository;


//classe per definire le operazioni CRUD tramite metodi java: descrive i servizi offerti
@Service
public class RecipeService {
	
	@Autowired private RecipeRepository recipeRepository;	//istanza costruita e inizializzata dal framework
	
	public Iterable<Recipe> getAllRecipes(){
		return this.recipeRepository.findAll();
	}

    public void save(Recipe recipe) {
        this.recipeRepository.save(recipe);
    }
	
	public Recipe getRecipeById(Long id) {
		return this.recipeRepository.findById(id).get();
	}

//	
//	public void deleteRecipeById(Long id) {
//		this.recipeRepository.deleteById(id);
//	}
}