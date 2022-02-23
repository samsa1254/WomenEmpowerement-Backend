package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import tn.esprit.spring.entities.Cagnotte;

import tn.esprit.spring.service.CagnotteService;

@Api(tags = "Cagnotte Management")

@RestController
@RequestMapping("/Cagnotte")

public class CagnotteRestController {
	@Autowired
	private CagnotteService CagnotteSer ;
	
	@GetMapping("/retrieve-all-cagnottes")
	@ApiOperation(value = "Récupérer la liste des cagnottes ")
	@ResponseBody
	public List<Cagnotte> getCagnottes()
	{
		List<Cagnotte> cagnottes = CagnotteSer.getAllCagnotte();
		return cagnottes;
	}
	
	@GetMapping("/retrieve-cagnotte/{id}")
	@ApiOperation(value = "recuperer un cagnotte  ")
	@ResponseBody
	public Cagnotte getCagnotte (@PathVariable("id") Long id)
	{
		return CagnotteSer.getCagnotteById(id);   
	}
	
	@PostMapping("/add-cagnotte")
	@ApiOperation(value = "ajouter un cagnotte ")
	@ResponseBody 
	public void addCagnotte(@RequestBody Cagnotte cagnotte )
	{
		CagnotteSer.addCagnotte(cagnotte);
		
		
	}
	
	@DeleteMapping("/remove-cagnotte/{id}")
	@ApiOperation(value = "supprimer un cagnotte ")
	@ResponseBody
	public void removeCagnotte(@PathVariable("id") Long id )
	{
		CagnotteSer.deleteCagnotte(id);
	}
	
	@PutMapping("/modify-cagnotte")
	@ApiOperation(value = "modifier un cagnotte ")
	@ResponseBody
	public void modifyCagnotte(@RequestBody Cagnotte cagnotte)
	{
		CagnotteSer.updateCagnotte(cagnotte);
		 
	}

}
