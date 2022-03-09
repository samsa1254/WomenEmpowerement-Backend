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
import tn.esprit.spring.entities.Candidacy;
import tn.esprit.spring.entities.Favorites;
import tn.esprit.spring.service.CandidacyService;
import tn.esprit.spring.service.FavService;

@Api("Favorites")
@RestController
@RequestMapping("/Favorites")
public class FavRestController {

	@Autowired
	private FavService fs;
	@Autowired
	private CandidacyService CS;
	
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
	
	
	@PostMapping("/Affect/{ido}/{idu}")
	@ResponseBody
	public void Affect( @RequestBody Favorites favs ,@PathVariable("ido") Long ido,@PathVariable("idu") int idu)
	{
		fs.Affect(favs, ido, idu);
		
	}

	
	@DeleteMapping("/DeleteFav/{id}")
	@ResponseBody
	public void removeCandid(@PathVariable("id") Long id )
	{
		fs.deleteFav(id);
	}
	
	
	
	
	@GetMapping("/GetFavsbyid/{id}")
	@ResponseBody
	public List<Favorites> GetFavorites (@PathVariable("id") int id)
	{
		return fs.getFavoritesByIdU(id); 
	}

}
