package it.uniroma3.MyRecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.MyRecipe.model.Provenienza;
import it.uniroma3.MyRecipe.model.Recipe;
import it.uniroma3.MyRecipe.service.*;
import jakarta.validation.Valid;


//classe che gestisce le richieste http tramite metodi Java
@Controller
public class RecipeController {

	@Autowired RecipeService recipeService;
	
	//mostra tutti i film
	@GetMapping("/recipes")
	public String showRecipes(Model model) {
		model.addAttribute("recipes", this.recipeService.getAllRecipes());
		return "recipes.html";
	}
	
	//riporta la pagina per modificare la lista dei film
	@GetMapping("/updateRecipes")
	public String updateRecipe(Model model) {
		model.addAttribute("recipes", this.recipeService.getAllRecipes());	//prendo l'id di recipe
		return "updateRecipes.html";
	}
	
	//riporta una pagina html che permette di aggiungere una nuova ricetta
	@GetMapping("/formNewRecipe")
	public String formNewRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		return "formNewRecipe.html";
	}
//
	//gestisce i dati, compresa la validazione, di una nuova ricetta
	@PostMapping("/recipe")
	public String saveRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())	//gli errori di validazione sono catturati da bindingResult
			return "formNewRecipe.html";
		else {
			this.recipeService.save(recipe); 
			model.addAttribute("recipes", this.recipeService.getAllRecipes());
			return "redirect:/recipes";
		}
	}
	
	//riporta una pagina html che permette di aggiungere una nuova provenienza alla ricetta aggiunta
	@GetMapping("/formNewProvenienza")
	public String formNewProvenienza(Model model) {
		model.addAttribute("provenienza", new Provenienza());
		return "formNewProvenienza.html";
	}

//
//	//riporta la pagina html della lista dei film ma con la possibilità di eliminarne uno
//	@GetMapping("/deleteRecipe")
//	public String showDeleteRecipePage(Model model) {
//		model.addAttribute("recipes", this.recipeService.getAllRecipes());
//		return "deleteRecipe.html";
//	}
//	
//	//metodo chiamato da showDeleteRecipePage, elimina un film dalla lista e ritorna la lista di film aggiornata
//	@GetMapping("/deleteRecipe/{id}")
//	public String deleteRecipe(@PathVariable("id") Long id, Model model) {
//		this.recipeService.deleteRecipeById(id);
//		model.addAttribute("recipes", this.recipeService.getAllRecipes());
//		return "recipes.html";
//	}
//		
//	//riporta la pagina per cercare un film
//	@GetMapping("/formSearchRecipes")
//	public String formSearchRecipes() {
//		return "formSearchRecipes.html";
//	}
//	
//	@GetMapping("/recipe/{id}")	//risponde a una richiesta del tipo: /recipe/3452
//	public String getRecipe(@PathVariable("id") Long id, Model model) {	//l'id preso dal path viene convertito in Long
//		//model permette di scambiare dati tra la Vista e il Controller
//		//con addAttribute() mettiamo a disposizione della Vista un oggetto che sarà il riferimento a "recipe" per la Vista
//		model.addAttribute("recipe", this.recipeService.getRecipeById(id));	//l'id viene passato al metodo
//		//la vista è recipe.html
//		return "recipe.html";
//	}
}