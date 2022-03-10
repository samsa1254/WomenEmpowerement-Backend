package tn.esprit.spring.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entities.*;
import tn.esprit.spring.mail.*;
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
	@Autowired
	EmailControllers EC;
	

	
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
	//lll
	
	@Override
	public Candidacy ApproveCandid(Long id , int s) {
		Candidacy c = CR.findById(id).get();
		
			if ((s<4) && (s>0)) {
		if (s==1)
		c.setState("Approved");
		else if (s==2)
		c.setState("Denied");
		else if (s==3)
		c.setState("On Hold");	
	    
		String no = c.getCandidName();
		String mu = c.getUser().getEmail();
		String un = c.getUser().getName();
		
		String st = c.getState();
	
		System.out.println(mu + st);
		EC.AcceptedMail(mu, un, no, st);
		CR.save(c);
			}			
		return c;
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
	
		String um = u.getEmail();
		String un = u.getName();
	    String pn = p.getName();
	    String da = date.toString();

		String nameoffer = p.getName();
		c.setCandidName(nameoffer);
		c.setDate_Of_Candid(date);
		c.setUser(u);
		c.setOffer(p);
		
		if(CR.save(c) != null)
		{
			EC.ApplicationMail(um, un, pn, da);
		}
		return c;

		
	}
	@Override
	public List<Candidacy> getCandidacyByOffer(Long idoff) {	
		return 	CR.getCandidacyByOffer(idoff);
	}
	@Override
	public List<Candidacy> FilterByState(String name ,String state) {	
		return CR.getCandidState(name , state);
	}
	@Override
	public List<Candidacy> getCandidacyByuser(int idu) {
		return CR.getUserCAn(idu);
	}
	
	
		
	

}
