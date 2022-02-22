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
import tn.esprit.spring.entities.Offer;
import tn.esprit.spring.service.OfferService;




@Api(tags = "Offer")
@RestController
@RequestMapping("/Offers")
public class OfferController {

	@Autowired
	private OfferService OS ;
	
	@GetMapping("/RetrieveOffer")
	@ResponseBody	
	public List<Offer> getOffer()
	{
		List<Offer> p = OS.retrieveallOffers();
		return p;
	}
	
	@GetMapping("/RetrieveOffer/{id}")
	@ResponseBody
	public Offer getOffer (@PathVariable("id") Long ido)
	{
		return OS.retrievebyId(ido);   
	}
	
	@PostMapping("/AddOffer")
	@ResponseBody 
	public Offer addOffer(@RequestBody Offer off )
	{
		Offer o= OS.addOffer(off);
		return o ; 
		
	}
	@DeleteMapping("/DeleteOffer/{id}")
	@ResponseBody
	public void removePub(@PathVariable("id") Long ido )
	{
		OS.deleteOfferById(ido);
	}
	
	@PutMapping("/EditOffer")
	@ResponseBody
	public Offer EditOffer(@RequestBody Offer offer)
	{
		Offer o = OS.updateOffer(offer);
		return o ; 
	}
}
