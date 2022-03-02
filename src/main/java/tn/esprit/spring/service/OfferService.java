package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Offer;

@Service
public interface OfferService {
	
	public Offer addOffer( Offer offer );
	public Offer updateOffer ( Offer off );
	public void deleteOfferById ( Long id );
	public Offer retrievebyId ( Long id );
	public List<Offer> retrieveallOffers ();
	public List<Offer> GetOfferByName(String name);
	
}
