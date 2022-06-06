package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.forbiden;
import tn.esprit.spring.service.forbidenService;

@Api(tags = "forbiden Management")
@CrossOrigin
@RestController
@RequestMapping("/forbiden")
public class forbidenRestController {
	
	@Autowired
	private forbidenService forbidenSer ;
	
	@GetMapping("/retrieve-forbiden/{id}") 
	@ApiOperation(value = "recuperer un forbiden  ")
	@ResponseBody
	public forbiden getforbiden (@PathVariable("id") Long id)
	{
		return forbidenSer.getforbidenword(id);   
	}
	
	@GetMapping("/retrieve-all")
	@ApiOperation(value = "Récupérer la liste des forbiden ")
	@ResponseBody
	public List<forbiden> getforbidens()
	{
		List<forbiden> forbidens = forbidenSer.getall();
		return forbidens;
	}
	
	@PostMapping("/add-forbiden")
	@ApiOperation(value = "ajouter un forbiden ")
	@ResponseBody 
	public void addad(@RequestBody forbiden forbiden )
	{
		 forbidenSer.addforbidenword(forbiden);
		 
		
	}
	
	@DeleteMapping("/remove-forbiden/{id}")
	@ApiOperation(value = "supprimer un forbiden ")
	@ResponseBody
	public void removeforbiden(@PathVariable("id") Long id )
	{
		forbidenSer.deleteforbidenword(id);
	}
	
	@PutMapping("/modify-forbiden")
	@ApiOperation(value = "modifier un forbiden ")
	@ResponseBody
	public void modifyAd(@RequestBody forbiden forbiden)
	{
		forbidenSer.updateforbidenword(forbiden);
		 
	}

}
