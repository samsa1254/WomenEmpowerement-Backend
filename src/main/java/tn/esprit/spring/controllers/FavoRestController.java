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
import tn.esprit.spring.entities.Candidacy;
import tn.esprit.spring.entities.Favorites;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.CandidacyService;
import tn.esprit.spring.service.FavService;
@CrossOrigin
@Api("Favorites")
@RestController
@RequestMapping("/Favorites")
public class FavoRestController {

	@Autowired
	private FavService fs;
	@Autowired
	private CandidacyService CS;
	@Autowired
	private UserRepository ur;
	
	@GetMapping("/GetAllFavorites")
	@ResponseBody
	public List<Favorites> getFavorites()
	{
		List<Favorites> c = fs.retrieveAll();
		return c;
	}
	
	@GetMapping("/GetFavs/{id}")
	@ResponseBody
	public Favorites getFavs (@PathVariable("id") Long id)
	{
		return fs.retrievebyID(id);   
	}
	
	
	@PostMapping("/Affect/{ido}")
	@ResponseBody
	public void Affect( @RequestBody Favorites favs ,@PathVariable("ido") Long ido)
	{
		SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User u = ur.findByLogin(auth.getName());
        int idu = u.getIduser() ; 
		fs.Affect(favs, ido, idu);
		
	}

	
	@DeleteMapping("/DeleteFav/{id}")
	@ResponseBody
	public void removeCandid(@PathVariable("id") Long id )
	{
		fs.deleteFav(id);
	}
	
	
	
	
	@GetMapping("/GetFavsbyid/")
	@ResponseBody
	public List<Favorites> GetFavorites ()
	{
		SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User u = ur.findByLogin(auth.getName());
        int id = u.getIduser() ; 
		return fs.getFavoritesByIdU(id); 
	}

}
