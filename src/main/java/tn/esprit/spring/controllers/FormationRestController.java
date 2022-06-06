package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Formation;
import tn.esprit.spring.service.FormationService;
@CrossOrigin
@RestController
@RequestMapping("/Formation")
public class FormationRestController {
	@Autowired
	private FormationService FormationSer ;
	
	
	@PostMapping("/add-affectformation/{idFormateur}")
	@ResponseBody
	public void ajouterEtAffceterFormationaFormateur( @RequestBody Formation formation ,@PathVariable("idFormateur") Long idFormateur)
	{
		FormationSer.ajouterEtAffceterFormationaFormateur(formation, idFormateur);
	}
	
	
	
	@GetMapping("/revenuFormation/{idFormation}")
	@ResponseBody
	public Integer getRevenuByFormation(@PathVariable("idFormation") Long idFormation) {
		return FormationSer.getRevenuByFormation(idFormation);
	}
	

}
