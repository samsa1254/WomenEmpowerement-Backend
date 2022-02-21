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
import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Publication;
import tn.esprit.spring.service.PublicationService;



@Api(tags = "Pub Management")
@RestController
@RequestMapping("/Publication")
public class PublicationRestController {

	@Autowired
	 private PublicationService PubSer ;
	
	@GetMapping("/retrieve-all-pubs")
	@ApiOperation(value = "Récupérer la liste des pubs ")
	@ResponseBody
	public List<Publication> getPubs()
	{
		List<Publication> pubs = PubSer.retrieveallPubs();
		return pubs;
	}
	
	@GetMapping("/retrieve-pub/{idPublication}")
	@ApiOperation(value = "recuperer un pub  ")
	@ResponseBody
	public Publication getPublication (@PathVariable("idPublication") Long idpub)
	{
		return PubSer.retrievebyId(idpub);   
	}
	
	@PostMapping("/add-pub")
	@ApiOperation(value = "ajouter un pub ")
	@ResponseBody 
	public Publication addPublication(@RequestBody Publication pub )
	{
		Publication p= PubSer.addPub(pub);
		return p ; 
		
	}
	@DeleteMapping("/remove-pub/{idPublication}")
	@ApiOperation(value = "supprimer un pub ")
	@ResponseBody
	public void removePub(@PathVariable("idPublication") Long idpub )
	{
		PubSer.deletepubById(idpub);
	}
	
	@PutMapping("/modify-pub")
	@ApiOperation(value = "modifier un pub ")
	@ResponseBody
	public Publication modifyPublication(@RequestBody Publication pub)
	{
		Publication p = PubSer.updatePub(pub);
		return p ; 
	}
}
