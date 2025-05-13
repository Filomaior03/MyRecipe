package it.uniroma3.MyRecipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//riporta la home
	@GetMapping("/")
	public String home(Model model) {
		return "home.html";
	}
}