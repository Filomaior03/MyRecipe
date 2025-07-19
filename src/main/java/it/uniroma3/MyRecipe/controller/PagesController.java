package it.uniroma3.MyRecipe.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.MyRecipe.model.Credenziali;
import it.uniroma3.MyRecipe.model.Ingrediente;
import it.uniroma3.MyRecipe.model.Ricetta;
import it.uniroma3.MyRecipe.model.Utente;
import it.uniroma3.MyRecipe.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



//classe che gestisce le richieste http tramite metodi Java
@Controller
public class PagesController {

	@Autowired RicettaService ricettaService;
	@Autowired IngredienteService ingredienteService;
	@Autowired CredenzialiService credenzialiService;

	//    private static final Logger logger = LoggerFactory.getLogger(PagesController.class);

	// Restituisce la home
	@GetMapping({"/", "/index"})
	public String showHome(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		//verifico se l'utente è autenticato
		boolean isAuthenticated = auth != null && auth.isAuthenticated() &&
				!(auth.getPrincipal() instanceof String && auth.getPrincipal().equals("anonymousUser"));

		boolean isAdmin = false;
		Utente u = null;

		//se è autenticato, verifico se è un admin
		if (isAuthenticated) {
			isAdmin = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(role -> role.equals("ADMIN"));

			String username = auth.getName();
			Credenziali cred = this.credenzialiService.getCredenzialiByUsername(username);

			//verifico le credenziali
			if (cred != null)
				u = cred.getUtente();

			model.addAttribute("userDetails", cred);  //utile per il logout
		}

		else
			model.addAttribute("userDetails", null);

		model.addAttribute("ricette", this.ricettaService.getAllRecipes());
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("utenteCorrente", u);

		return "index";
	}

	//mostra la pagina di una ricetta specifica (id) esistente
	@GetMapping("/recipe/{id}")
	public String showRecipe(@PathVariable("id") Long id, Model model) {
		Ricetta ricetta = this.ricettaService.getRecipeById(id);
		model.addAttribute("ricetta", ricetta);		
		model.addAttribute("ingredienti", ricetta.getIngredienti());

		//verifico se l'utente che accede alle ricette è un admin
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(role -> role.equals("ADMIN"));

		String username = auth.getName();
		Utente u = credenzialiService.getCredenzialiByUsername(username).getUtente();

		model.addAttribute("utenteCorrente", u);
		model.addAttribute("isAdmin", isAdmin);

		return "recipe";
	}

	//riporta una pagina html che permette di creare una nuova ricetta
	@GetMapping("/newRecipe")
	public String newRecipe(Model model) {
		model.addAttribute("ricetta", new Ricetta());
		model.addAttribute("ingredienti", this.ingredienteService.getAllIngredients());
		return "newRecipe";
	}

	//con @RequestParam catturiamo i parametri della richiesta http, (che viene inviata una volta compilata la form)
	@PostMapping("/newRecipe")
	public String saveNewRecipe(@Valid @ModelAttribute("ricetta") Ricetta ric, BindingResult bindingResult,
			@RequestParam("copertinaFile") MultipartFile copertinaFile, 
			@RequestParam(value = "listaIngredienti", required = false) List<Long> listaIngredienti, Model model)  {

		if(listaIngredienti == null || listaIngredienti.isEmpty()) {
			bindingResult.reject("error.listaIngredienti");	//messaggio di errore dentro errorMessages.properties
			model.addAttribute("ingredienti", this.ingredienteService.getAllIngredients());
			return "newRecipe";	//torno alla stessa pagina
		}

		if(bindingResult.hasErrors()) {	//gli errori di validazione sono catturati da bindingResult
			model.addAttribute("ingredienti", this.ingredienteService.getAllIngredients());
			return "newRecipe";	//torno alla stessa pagina
		}

		if (!copertinaFile.isEmpty()) {
			try {
				// Salva il file nella cartella "copertine" dentro /static/images (es.
				// src/main/resources/static/images/)
				String nomeFile = System.currentTimeMillis() + "_" + copertinaFile.getOriginalFilename();
				Path percorso = Paths.get("src/main/resources/static/images/" + nomeFile);

				// Assicura che la directory esista
				Files.createDirectories(percorso.getParent());

				// Salva il file
				Files.write(percorso, copertinaFile.getBytes());

				// Imposta il path (visibile via web) nel libro
				ric.setImg("images/" + nomeFile);

			} catch (IOException e) {
				e.printStackTrace();
				// Puoi aggiungere logging o messaggi di errore nel model
			}
		}

		//inserisco gli ingredienti selezionati alla nuova ricetta
		if (listaIngredienti != null && !listaIngredienti.isEmpty()) {			
			List<Ingrediente> ingredientiSelezionati = new ArrayList<>();

			for (Long ingrID : listaIngredienti) {
				Ingrediente ingr = ingredienteService.getIngredientById(ingrID);
				if (ingr != null) {
					ingredientiSelezionati.add(ingr);
				}
			}
			ric.setIngredienti(ingredientiSelezionati);	//aggiungo gli ingredienti alla ricetta corrente
		}

		//aggiungo alle ricette create dall'utente autenticato la nuova ricetta creata
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Utente u = credenzialiService.getCredenzialiByUsername(username).getUtente();

		ric.setUtente(u);	//lego l'utente alla ricetta che ha creato
		ricettaService.save(ric);
		return "redirect:/index";
	}

	//riporta ad una pagina che permette di aggiungere un nuovo ingrediente ad una ricetta,
	//o di selezionarne uno già presente
	@GetMapping("/addIngredient")
	public String newIngredient(Model model) {
		model.addAttribute("ingr", new Ingrediente());
		model.addAttribute("ingredienti", ingredienteService.getAllIngredients());
		return "addIngredient";
	}

	//gestisce i dati inviati dalla form, tiene conto degli ingredienti aggiunti
	@PostMapping("/addIngredient")
	public String addIngredient(@Valid @ModelAttribute("ingr") Ingrediente ingr, BindingResult bindingResult, Model model, HttpSession session) {

		//errori nella compilazione dei form
		if(bindingResult.hasErrors()) {
			model.addAttribute("ingredienti", ingredienteService.getAllIngredients());
			return "addIngredient";
		}

		//tentativo di aggiungere un ingrediente già presente nel DB
		else if(this.ingredienteService.existIngredientByName(ingr.getNome())) {
			bindingResult.rejectValue("nome", "error.nomeIngrediente");	//messaggio di errore dentro errorMessages.properties

			//ritorno la pagina per la scelta degli ingredienti dopo aver recuperato la lista di tutti gli ingredienti presenti
			model.addAttribute("ingredienti", ingredienteService.getAllIngredients());
			return "addIngredient";
		}

		ingredienteService.save(ingr);	//salvo il nuovo ingrediente nel DB

		//ricarico la lista aggiornata degli ingredienti
		model.addAttribute("ingredienti", ingredienteService.getAllIngredients());
		model.addAttribute("ingr", new Ingrediente());

		return "addIngredient";
	}

	//riporta la pagina per modificare una ricetta
	@GetMapping("/changeRecipe/{id}")
	public String changeRecipe(Model model, @PathVariable Long id) {
		model.addAttribute("ricetta", ricettaService.getRecipeById(id));
		model.addAttribute("ingredienti", ingredienteService.getAllIngredients());
		return "changeRecipe";
	}

	@PostMapping("/changeRecipe")
	public String postModificaLibro(@ModelAttribute Ricetta ricetta,
			@RequestParam("copertinaFile") MultipartFile copertinaFile,
			@RequestParam("listaIngredienti") List<Long> listaIngredienti) {

		Ricetta ricettaOriginale = this.ricettaService.getRecipeById(ricetta.getId());

		if (!copertinaFile.isEmpty()) {
			try {
				// Salva il file nella cartella "images"
				// src/main/resources/static/images/)
				String nomeFile = System.currentTimeMillis() + "_" + copertinaFile.getOriginalFilename();
				Path percorso = Paths.get("src/main/resources/static/images/" + nomeFile);

				// Assicura che la directory esista
				Files.createDirectories(percorso.getParent());

				// Salva il file
				Files.write(percorso, copertinaFile.getBytes());

				// Imposta il path (visibile via web) nella ricetta
				ricetta.setImg("images/" + nomeFile);

			} catch (IOException e) {
				e.printStackTrace();
				// Puoi aggiungere logging o messaggi di errore nel model
			}
		} else {
			ricetta.setImg((String) ricettaService.getRecipeById(ricetta.getId()).getImg());
		}

		if (listaIngredienti != null && !listaIngredienti.isEmpty()) {
			List<Ingrediente> ingredientiSelezionati = new ArrayList<>();
			for (Long ingredienteId : listaIngredienti) {
				Ingrediente ingr = ingredienteService.getIngredientById(ingredienteId);
				if (ingr != null) {
					ingredientiSelezionati.add(ingr);
				}
			}
			ricetta.setIngredienti(ingredientiSelezionati);
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Utente u = credenzialiService.getCredenzialiByUsername(username).getUtente();

		if(u.getCredentials().getRuolo().equals("ADMIN"))
			ricetta.setUtente(ricettaOriginale.getUtente());//lego l'utente corrente alla ricetta modificata
		else
			ricetta.setUtente(u);

		ricettaService.save(ricetta);
		return "redirect:/index";
	}

	@GetMapping("/deleteRecipe/{id}")
	public String deleteRecipe(@PathVariable("id") Long id, Model model) {

		//verifico se l'utente che accede alle ricette è un admin
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(role -> role.equals("ADMIN"));

		String username = auth.getName();
		Utente u = credenzialiService.getCredenzialiByUsername(username).getUtente();

		model.addAttribute("utenteCorrente", u);
		model.addAttribute("isAdmin", isAdmin);

		this.ricettaService.deleteRecipeById(id);

		return "redirect:/index";
	}
}