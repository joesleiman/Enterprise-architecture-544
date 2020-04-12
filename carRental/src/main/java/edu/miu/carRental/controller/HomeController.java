package edu.miu.carRental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@CrossOrigin(allowedHeaders = "*")
public class HomeController {
	
	@GetMapping("/")
	public String displayHome() {
		return "home/login";
	}
	@GetMapping("/login")
	public String displayLogin() {
		return "home/login";
	}
	@GetMapping("/logout-success")
	public String displayLogout() {
		return "home/index";
	}

	@PostMapping("/login")
	public String userLogin() {
		
		return "redirect:/employee/ncars";
	}
	
}
