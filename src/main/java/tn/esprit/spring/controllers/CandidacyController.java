package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import tn.esprit.spring.entities.Candidacy;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CandidacyRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.CandidacyService;

@Api("Candidacy")
@RestController
@RequestMapping("/Candidacy")
@CrossOrigin

public class CandidacyController {
	@Autowired
	private CandidacyService CS;
	@Autowired
	private UserRepository ur;
	
	

	@GetMapping("/GetAllCandid")
	@ResponseBody
	public List<Candidacy> getCandid()
	{
		List<Candidacy> c = CS.retrieveAll();
		return c;
	}
	
	@GetMapping("/GetCandid/{id}")
	@ResponseBody
	public Candidacy getReact (@PathVariable("id") Long id)
	{
		return CS.retrievebyID(id);   
	}
	
	
	@PostMapping("/Affect/{ido}/{idu}")
	@ResponseBody
	public void Affect( @RequestBody Candidacy Candidacy ,@PathVariable("ido") Long ido,@PathVariable("idu") int idu)
	{
		SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User u = ur.findByLogin(auth.getName());
        //int idu = u.getIduser() ; 
		CS.Affect(Candidacy, ido, idu);
	}

	
	@DeleteMapping("/DeleteCandid/{id}")
	@ResponseBody
	public void removeCandid(@PathVariable("id") Long id )
	{
		CS.deleteCandidacy(id);
	}
	
	@PutMapping("/UpdateCandid")
	@ResponseBody
	public Candidacy UpdateCandid(@RequestBody Candidacy cand)
	{
		
		return CS.updatereCandidacy(cand); 
	}
	@PutMapping("/ApproveCandid/{id}/{s}")
	@ResponseBody
	public Candidacy CandidacyApproveCandid(@PathVariable("id") Long id , @PathVariable("s") int s  )
	{
		
		return CS.ApproveCandid(id, s); 
	}
	
	@GetMapping("/GetCandidbyOffer/{id}")
	@ResponseBody
	public List<Candidacy> GetCandidByOffer (@PathVariable("id") Long id)
	{
		return CS.getCandidacyByOffer(id);   
	}
	
	@GetMapping("/GetCandidbyState/{name}/{state}")
	@ResponseBody
	public List<Candidacy> GetCandidbyState (@PathVariable("name") String name ,@PathVariable("state") String state)
	{
		return CS.FilterByState(name , state);   
	}
	
	@GetMapping("/GetCandidbyuser")
	@ResponseBody
	public List<Candidacy> GetCandidByuser ()
	{
		SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User u = ur.findByLogin(auth.getName());
        int id = u.getIduser() ; 
        
        
		return CS.getCandidacyByuser(id);   
	}

}
