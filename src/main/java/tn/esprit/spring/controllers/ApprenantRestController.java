package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Apprenant;
import tn.esprit.spring.service.ApprenantService;

@RestController
@RequestMapping("/Apprenant")
@CrossOrigin

public class ApprenantRestController {
	@Autowired
	private ApprenantService ApprenantSer;
	
	
	@PostMapping("/add-apprenant")
	@ResponseBody
	public void ajouterApprenant(@RequestBody  Apprenant apprenant)
	{
		ApprenantSer.ajouterApprenant(apprenant);
	}
	
	
	@PostMapping("/affect-app-form/{idApprenant}/{idFormation}")
	@ResponseBody
	public void affecterApprenantFormation(@PathVariable("idApprenant") Long idApprenant , @PathVariable("idFormation") Long idFormation)
	{
		ApprenantSer.affecterApprenantFormation(idApprenant, idFormation);
	}
	
	
	

}
