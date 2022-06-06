package tn.esprit.spring.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
import tn.esprit.spring.entities.Publication;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.PublicationService;

@CrossOrigin

@Api(tags = "Pub Management")
@RestController
@RequestMapping("/Publication")
public class PublicationRestController {

	@Autowired
	 private PublicationService PubSer ;
	@Autowired
	private UserRepository UserRep ;
	
	
	
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
	public String addPublication(@RequestBody Publication pub )
	{
		SecurityContext context = SecurityContextHolder.getContext();
	    Authentication auth = context.getAuthentication();
	   User u = UserRep.findByLogin(auth.getName());
	     int id = u.getIduser() ; 
	return  PubSer.addPub(pub, id );
		
		
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
	public String modifyPublication(@RequestBody Publication pub)
	{
		 return  PubSer.updatePub(pub);
		 
	}
	@GetMapping("/retrieve-pubtendency")
	@ApiOperation(value = "recuperer les tendances pub  ")
	@ResponseBody
	public List<Publication> getPublicationtendency ( )
	{
		return PubSer.tendency();   
	}
	
	@GetMapping("/retrieve-pubreact")
	@ApiOperation(value = "recuperer les  pub reacted  ")
	@ResponseBody
	public List<Publication> getPublicationmostreact ( )
	{
		return PubSer.MostReacted();   
	}
	
	
	@GetMapping("/retrieve-3react/{idPublication}")
	@ApiOperation(value = "recuperer les  3reacted  ")
	@ResponseBody
	public List<String> getPublication3react ( @PathVariable("idPublication") Long idPublication)
	{
		return PubSer.reacts(idPublication);  
	}

	
	
}
