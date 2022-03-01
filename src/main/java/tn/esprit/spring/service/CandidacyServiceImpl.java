package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entities.*;

import tn.esprit.spring.service.*;
import tn.esprit.spring.repository.*;

@Service
public class CandidacyServiceImpl  implements CandidacyService{
	@Autowired
	private CandidacyRepository CR ; 
	@Autowired
	private OfferRepository OR;
	@Autowired
	private UserRepository UR;
	
	
	@Override
	public Candidacy retrievebyID(Long id) {
		Candidacy r = CR.findById(id).get();
		return r;
	}
	@Override
	public List<Candidacy> retrieveAll() {
		List<Candidacy> r = (List<Candidacy>) CR.findAll();
		return r;
	}
	
	@Override
	public Candidacy updatereCandidacy(Candidacy Candidacy) {
		
		CR.save(Candidacy);
		
		return Candidacy;
	}
	@Override
	public void deleteCandidacy(Long id) {
		CR.deleteById(id);
		
	}
	
	@Override
	public Candidacy Affect(Candidacy c,Long ido,int idu) {
	    java.util.Date date = new java.util.Date(); 
	    
	    
		Offer p = OR.findById(ido).get();	
		User u = UR.findById(idu).get();
		
		String nameoffer = p.getName();
		c.setCandidName(nameoffer);
		c.setDate_Of_Candid(date);
		c.setUser(u);
		c.setOffer(p);
		CR.save(c);
		return c;

	}
	
	

}
