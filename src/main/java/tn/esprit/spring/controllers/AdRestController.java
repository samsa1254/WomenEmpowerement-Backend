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

import tn.esprit.spring.service.AdService;


@Api(tags = "Ad Management")
@CrossOrigin
@RestController
@RequestMapping("/Ad")

public class AdRestController {

	@Autowired
	 private AdService AdSer ;
	
	@GetMapping("/retrieve-all-ads")
	@ApiOperation(value = "Récupérer la liste des ads ")
	@ResponseBody
	public List<Ad> getAds()
	{
		List<Ad> ads = AdSer.retrieveAllAds();
		return ads;
	}
	
	@GetMapping("/retrieve-ad/{idAd}")
	@ApiOperation(value = "recuperer un ad  ")
	@ResponseBody
	public Ad getAd (@PathVariable("idAd") Long idAd)
	{
		return AdSer.retrievebyId(idAd);   
	}
	
	@PostMapping("/add-ad")
	@ApiOperation(value = "ajouter un ad ")
	@ResponseBody 
	public Ad addad(@RequestBody Ad ad )
	{
		Ad a = AdSer.addad(ad);
		return a ; 
		
	}
	
	@DeleteMapping("/remove-ad/{idAd}")
	@ApiOperation(value = "supprimer un ad ")
	@ResponseBody
	public void removeAd(@PathVariable("idAd") Long idAd )
	{
		AdSer.deleteAd(idAd);
	}
	
	@PutMapping("/modify-ad")
	@ApiOperation(value = "modifier un ad ")
	@ResponseBody
	public Ad modifyAd(@RequestBody Ad ad)
	{
		Ad a = AdSer.updateAd(ad);
		return a ; 
	}
}
