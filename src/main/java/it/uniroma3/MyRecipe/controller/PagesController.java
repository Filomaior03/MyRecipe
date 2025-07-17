package it.uniroma3.MyRecipe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.MyRecipe.model.Ingrediente;
import it.uniroma3.MyRecipe.model.Ricetta;
import it.uniroma3.MyRecipe.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



//classe che gestisce le richieste http tramite metodi Java
@Controller
public class PagesController {

	@Autowired RicettaService ricettaService;
	@Autowired IngredienteService ingredienteService;

	//restituisce la home - PUBBLICA
	@GetMapping({"/", "/index"}) 
	public String showHome(Model model) {
		model.addAttribute("ricette", this.ricettaService.getAllRecipes());
		return "index";
	}

	//mostra la pagina di una ricetta specifica (id) esistente
	@GetMapping("/recipe/{id}")
	public String showRecipe(@PathVariable("id") Long id, Model model) {
		Ricetta ricetta = this.ricettaService.getRecipeById(id);
		model.addAttribute("ricetta", ricetta);		
		model.addAttribute("ingredienti", ricetta.getIngredienti());	
		return "recipe";
	}

	//riporta una pagina html che permette di creare una nuova ricetta
	@GetMapping("/newRecipe")
	public String newRecipe(Model model) {
		model.addAttribute("ricetta", new Ricetta());
		return "newRecipe";
	}

	//riceve i dati e valida i campi di una nuova ricetta dopo averla appena creata
	@PostMapping("/newRecipe")
	public String saveNewRecipe(@Valid @ModelAttribute("ricetta") Ricetta ricetta, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {	//gli errori di validazione sono catturati da bindingResult
			return "newRecipe";	//torno alla stessa pagina
		}
		else {
			//this.ricettaService.save(ricetta);	//dopo aver ricevuto i dati della form, salvo la ricetta (ora con un id)
			return "redirect:/ingredient"; //reindirizzo alla pagina per la scelta degli ingredienti -> viene invocata la GET /ingredient
		}
	}

	//riporta ad una pagina che permette di aggiungere un nuovo ingrediente ad una ricetta,
	//o di selezionarne uno già presente
	@GetMapping("/ingredient")
	public String newIngredient(Model model) {
		model.addAttribute("ingr", new Ingrediente());
		model.addAttribute("ingredienti", ingredienteService.getAllIngredients());
		return "ingredient";
	}

	//gestisce i dati inviati dalla form, tiene conto degli ingredienti aggiunti
	@PostMapping("/ingredient")
	public String addIngredient(@ModelAttribute("ingr") Ingrediente ingr, BindingResult bindingResult, Model model, HttpSession session) {

		//errori nella compilazione dei form
		if(bindingResult.hasErrors()) {
			model.addAttribute("ingredienti", ingredienteService.getAllIngredients());
			return "ingredient";
		}

		//tentativo di aggiungere un ingrediente già presente nel DB
		else if(this.ingredienteService.existIngredientByName(ingr.getNome())) {
			bindingResult.rejectValue("nome", "error.nomeIngrediente");	//messaggio di errore dentro errorMessages.properties

			//ritorno la pagina per la scelta degli ingredienti dopo aver recuperato la lista di tutti gli ingredienti presenti
			model.addAttribute("ingredienti", ingredienteService.getAllIngredients());
			return "ingredient";
		}

		ingredienteService.save(ingr);	//salvo il nuovo ingrediente nel DB

		//ricarico la lista aggiornata degli ingredienti
		model.addAttribute("ingredienti", ingredienteService.getAllIngredients());
		model.addAttribute("ingr", new Ingrediente());

		return "ingredient";
	}

	//riporta la pagina per modificare una ricetta
	@GetMapping("/confirmIngredients")
	public String confirmIngredients(Model model) {
		model.addAttribute("ingredienti", this.ingredienteService.getAllIngredients());
		return "confirmIngredients";
	}

	//lega gli ingredienti selezionati alla ricetta corrente
	@SuppressWarnings("unchecked")
	@PostMapping("/confirmIngredients")
	public String confirmIngredients(@RequestParam("idRicetta") Long idRicetta,  Ingrediente ingr, BindingResult bindingResult, Model model, HttpSession session) {

		Ricetta rec = this.ricettaService.getRecipeById(idRicetta);

		//recupero gli ingredienti aggiunti nella sessione
		List<Ingrediente> ingredientiAggiunti = (List<Ingrediente>)session.getAttribute("ingredientiAggiunti");

		//aggiungo gli ingredienti aggiunti alla ricetta corrente
		if(ingredientiAggiunti != null)
			rec.setIngredienti(ingredientiAggiunti);
		this.ricettaService.save(rec);

		//ricetta completata, resetto la lista di ingredienti aggiunti
		session.removeAttribute("ingredientiAggiunti");

		model.addAttribute("ricetta", new Ricetta());
		model.addAttribute("ingredienti", rec.getIngredienti());	//riferimento a ingredienti dentro Ricetta

		return "recipe" + idRicetta;
	}

	//	//riporta la pagina html della lista dei film ma con la possibilità di eliminarne uno
	//	@GetMapping("/deleteRecipe")
	//	public String showDeleteRecipePage(Model model) {
	//		model.addAttribute("recipes", this.ricettaService.getAllRecipes());
	//		return "deleteRecipe.html";
	//	}
	//
	//	//metodo chiamato da showDeleteRecipePage, elimina un film dalla lista e ritorna la lista di film aggiornata
	//	@GetMapping("/deleteRecipe/{id}")
	//	public String deleteRecipe(@PathVariable("id") Long id, Model model) {
	//		this.ricettaService.deleteRecipeById(id);
	//		model.addAttribute("recipes", this.ricettaService.getAllRecipes());
	//		return "recipes.html";
	//	}
	//
	//	//riporta la pagina per cercare un film
	//	@GetMapping("/formSearchRecipes")
	//	public String formSearchRecipes() {
	//		return "formSearchRecipes.html";
	//	}

}