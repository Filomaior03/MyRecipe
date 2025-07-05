package it.uniroma3.MyRecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.MyRecipe.model.Ingrediente;
import it.uniroma3.MyRecipe.model.Ricetta;
import it.uniroma3.MyRecipe.service.*;
import jakarta.validation.Valid;



//classe che gestisce le richieste http tramite metodi Java
@Controller
public class Pages {

	@Autowired RicettaService recipeService;

	//mostra la lista di tutte  le ricette
	@GetMapping("/recipes")
	public String showRecipes(Model model) {
		model.addAttribute("recipes", this.recipeService.getAllRecipes());
		return "recipes.html";
	}

	//riporta la pagina per modificare una ricetta
	@GetMapping("/updateRecipes")
	public String updateRecipe(Model model) {
		model.addAttribute("recipes", this.recipeService.getAllRecipes());
		return "updateRecipes.html";
	}

	//riporta una pagina html che permette di creare una nuova ricetta
	@GetMapping("/formNewRecipe")
	public String formNewRecipe(Model model) {
		model.addAttribute("recipe", new Ricetta());
		return "formNewRecipe.html";
	}

	//riceve i dati e valida i campi di una nuova ricetta dopo averla appena creata
	@PostMapping("/recipe")
	public String saveNewRecipe(@Valid @ModelAttribute("recipe") Ricetta recipe, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())	//gli errori di validazione sono catturati da bindingResult
			return "formNewRecipe.html";	//torno alla stessa pagina

		else {
			this.recipeService.save(recipe);	//dopo aver ricevuto i dati della form, salvo la ricetta (ora con un id)
			return "redirect:/recipe/" + recipe.getId();	//torno la ricetta appena creata (vedi showRecipe)
		}
	}

	//mostra la pagina di una ricetta specifica (id) esistente
	@GetMapping("/recipe/{id}")
	public String showRecipe(@PathVariable("id") Long id, Model model) {
		Ricetta recipe = this.recipeService.getRecipeById(id);
		model.addAttribute("recipe", recipe);
		model.addAttribute("ingredients", recipe.getIngredienti());	
		return "recipe.html";
	}

	@GetMapping("/formNewIngredient")
	public String formNewIngredient(Model model) {
		model.addAttribute("ingr", new Ingrediente());
		return "formNewIngredient.html";
	}
	
//	//riporta ad una pagina che permette di aggiungere un nuovo ingrediente ad una ricetta
//	@PostMapping("/formNewIngredient")
//	public String formNewIngredient(@ModelAttribute("ingr") Ingrediente ingr, BindingResult bindingResult, Model model) {
//		if(bindingResult.hasErrors())
//			return "formNewIngredient.html";
//
//		else {
//			model.addAttribute("ingr", new Ingrediente());
//			return "/recipe";
//		}
//	}
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