package it.uniroma3.MyRecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.MyRecipe.model.Credenziali;
import it.uniroma3.MyRecipe.model.Utente;
import it.uniroma3.MyRecipe.service.CredenzialiService;
import it.uniroma3.MyRecipe.service.UtenteService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller			
public class AuthenticationController {

	@Autowired
	private CredenzialiService credenzialiService;

	@Autowired
	private UtenteService utenteService;

	//restituisce la pagina per il login - PUBBLICA
	@GetMapping("/login")
	public String showLoginForm() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			//utente già loggato, redirect alla dashboard
			return "redirect:/index";
		}
		//utente non loggato, torna alla pagina per fare il login
		return "login";
	}

	//restituisce la pagina per la registrazione - PUBBLICA
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credenziali", new Credenziali());
		return "register";
	}

	@PostMapping("/register")
	public String saveCredenziali(@Valid @ModelAttribute("utente") Utente utente, BindingResult brU, 
			@Valid @ModelAttribute("credenziali") Credenziali credenziali, BindingResult brC, Model model) {
		if(brU.hasErrors() || brC.hasErrors())
			return "register";

		//la conferma della password non va a buon fine
		if(!credenziali.getPassword().equals(credenziali.getConfirmPassword())) {
			//messaggio di errore
			brC.rejectValue("confirmPassword", "error.confirmPassword", "Le password non coincidono");
			return "register";
		}
		
		//salvo le credenziali dell'utente registrato, ASSEGNANDOGLI IL RUOLO "UTENTE"
		this.credenzialiService.saveCredenziali(credenziali);
		
		//lego le credenziali all'utente appena registrato
		utente.setCredentials(credenziali);
		credenziali.setUtente(utente);	

		this.utenteService.saveUtente(utente);	//salvo l'utente (e di conseguenza le sue credenziali)
						
		model.addAttribute("utente", utente);
		return "redirect:/login";	//reindirizzo il nuovo utente alla pagina di login per autenticarsi
	}
}