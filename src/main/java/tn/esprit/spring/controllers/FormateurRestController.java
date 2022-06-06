package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Formateur;
import tn.esprit.spring.service.FormateurService;
@CrossOrigin
@RestController
@RequestMapping("/Formateur")
public class FormateurRestController {
	
	@Autowired
	private FormateurService FormateurSer;

	
	@PostMapping("/add-formateur")
	@ResponseBody
	public void ajouterFormateur(@RequestBody Formateur formateur)
	{
		FormateurSer.ajouterFormateur(formateur);
	}
}
