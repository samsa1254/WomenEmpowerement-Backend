package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Offer;
import tn.esprit.spring.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {
	
	@Autowired
	private OfferRepository OR ; 
	@Override
	public Offer addOffer(Offer offer) {
	OR.save(offer);
		return null;
	}
	@Override
	public Offer updateOffer(Offer off) {
		OR.save(off);
		return null;
	}
	@Override
	public void deleteOfferById(Long id) {
		OR.deleteById(id);
		
	}
	@Override
	public Offer retrievebyId(Long id) {
		Offer o = OR.findById(id).get();
		return o;
	}

	@Override
	public List<Offer> retrieveallOffers() {
		List<Offer> o = (List<Offer>) OR.findAll();
		return o;
	}


}
