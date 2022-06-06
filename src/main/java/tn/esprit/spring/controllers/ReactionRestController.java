package tn.esprit.spring.controllers;

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
import tn.esprit.spring.entities.Reaction;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.ReactionService;

@Api(tags = "Reaction Management")

@RestController
@RequestMapping("/Reaction")
@CrossOrigin

public class ReactionRestController {
	@Autowired
	private ReactionService reactionSer;
	@Autowired
	private UserRepository UserRep ;
	
	@GetMapping("/retrieve-all-reactss")
	@ApiOperation(value = "Récupérer la liste des reactss ")
	@ResponseBody
	public List<Reaction> getReacts()
	{
		List<Reaction> reacts = reactionSer.retrieveAll();
		return reacts;
	}
	
	@GetMapping("/retrieve-reacts/{id}")
	@ApiOperation(value = "recuperer un Reactt  ")
	@ResponseBody
	public Reaction getReact (@PathVariable("id") Long id)
	{
		return reactionSer.retrievebyID(id);   
	}
	
	
	@PostMapping("/add-affectreaction/{idPublication}")
	@ResponseBody
	public void ajouterEtAffceterreactionPub( @RequestBody Reaction reaction ,@PathVariable("idPublication") Long idPublication)
	{
		SecurityContext context = SecurityContextHolder.getContext();
	    Authentication auth = context.getAuthentication();
	   User u = UserRep.findByLogin(auth.getName());
	     int id = u.getIduser() ; 
		reactionSer.addReaction(reaction, idPublication ,id);
	}

	
	@DeleteMapping("/remove-react/{id}")
	@ApiOperation(value = "supprimer un react ")
	@ResponseBody
	public void removeReact(@PathVariable("id") Long id )
	{
		reactionSer.deleteReaction(id);
	}
	
	@PutMapping("/modify-react")
	@ApiOperation(value = "modifier un react ")
	@ResponseBody
	public Reaction modifyReact(@RequestBody Reaction r)
	{
		
		return reactionSer.updatereReaction(r); 
	}
}
