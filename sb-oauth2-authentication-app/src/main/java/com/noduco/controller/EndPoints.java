package com.noduco.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EndPoints {
	
	@GetMapping("/message")
	public String messagePrint() {
		return "Helloworld";
	}
	
	  @GetMapping("/")
	    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
	        if (principal != null) {
	            model.addAttribute("name", principal.getFullName());
	        }
	        return "home";
	    }
	  
	  @GetMapping("/secure")
		public String securedMessage() {
			return "Hello Secured!";
		}

}
