package it.uniroma3.MyRecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.MyRecipe.model.Credentials;
import it.uniroma3.MyRecipe.model.Utente;
import it.uniroma3.MyRecipe.service.UtenteService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthenticationController {
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping(value = "/login")
	public String showLoginForm() {
		return "formLogin";
	}
	
	@GetMapping(value = "/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credentials", new Credentials());
		return "formRegisterUtente";
	}	
}