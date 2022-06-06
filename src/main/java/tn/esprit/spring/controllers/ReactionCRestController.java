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

import tn.esprit.spring.entities.ReactionC;

import tn.esprit.spring.service.ReactionCService;

@Api(tags = "ReactionC Management")

@RestController
@RequestMapping("/ReactionC")
@CrossOrigin

public class ReactionCRestController {

	@Autowired
	private ReactionCService reactionCSer;
	
	
	@GetMapping("/retrieve-all-reacts")
	@ApiOperation(value = "Récupérer la liste des reacts ")
	@ResponseBody
	public List<ReactionC> getReacts()
	{
		List<ReactionC> reacts = reactionCSer.retrieveAll();
		return reacts;
	}
	
	@GetMapping("/retrieve-react/{id}")
	@ApiOperation(value = "recuperer un React  ")
	@ResponseBody
	public ReactionC getReact (@PathVariable("id") Long id)
	{
		return reactionCSer.retrievebyID(id);   
	}
	
	@PostMapping("/add-affectreactionc/{idCom}")
	@ResponseBody
	public void ajouterEtAffceterreactionCom( @RequestBody ReactionC reactionC ,@PathVariable("idCom") Long idCom)
	{
		reactionCSer.addReactionC(reactionC, idCom);
	}
	
	@DeleteMapping("/remove-react/{id}")
	@ApiOperation(value = "supprimer un react ")
	@ResponseBody
	public void removeReact(@PathVariable("id") Long id )
	{
		reactionCSer.deleteReactionC(id);
	}
	
	
	@PutMapping("/modify-react")
	@ApiOperation(value = "modifier un react ")
	@ResponseBody
	public ReactionC modifyReact(@RequestBody ReactionC c)
	{
		
		return reactionCSer.updatereReactionC(c); 
	}
}
