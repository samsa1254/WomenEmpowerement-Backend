package tn.esprit.spring.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entities.Publication;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.forbiden;
import tn.esprit.spring.repository.PublicationRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.repository.forbidenRepository;

@Service
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private PublicationRepository PublicationRep ; 
	@Autowired
	private forbidenRepository forbidenRep ;
	@Autowired
	private UserRepository UserRep ;
	final public LocalTime time = LocalTime.now();
	final public LocalDate date = LocalDate.now().minusDays(3);
	@Override
	public String addPub(Publication pub , int id ) {
		String textbody= pub.getPost();
		User u =UserRep.findById(id).get(); 
		pub.setUser(u);
		List<forbiden> badwordlist = (List<forbiden>) forbidenRep.findAll();
		int compteur=0;
		for(int i=0 ; i<badwordlist.size(); i++)
		{
			if (textbody.contains(badwordlist.get(i).getText()))
			{
				compteur++; 
			}
		}
		if (compteur>0)
		{
			return "your post contains "+compteur+" bad words";
			
		}
		else	
				{
		
		
			
			 PublicationRep.save(pub);
			 return "Post added successfuly " ;
				}
	}
		
	

	@Override
	public String  updatePub(Publication pub) {
		String textbody= pub.getPost();
		List<forbiden> badwordlist = (List<forbiden>) forbidenRep.findAll();
		int compteur=0;
		for(int i=0 ; i<badwordlist.size(); i++)
		{
			if (textbody.contains(badwordlist.get(i).getText()))
			{
				compteur++; 
			}
		}
		if (compteur>0)
		{
			return "your post contains "+compteur+" bad words";
			
		}
		else	
				{
			
			 PublicationRep.save(pub);
			 return "Post added successfuly " ;
				}
	}

	@Override
	public void deletepubById(Long id) {
		PublicationRep.deleteById(id);
		
	}

	@Override
	public Publication retrievebyId(Long id) {
		Publication p = PublicationRep.findById(id).get();
		return p ;
		}

	@Override
	public List<Publication> retrieveallPubs() {
		List<Publication> pubs = (List<Publication>) PublicationRep.findAll();
		return pubs;
	}

	@Override
	public List<Publication> tendency() {
	
		
		List<Publication> pubstendency =  new ArrayList<Publication>();
		List<Long> pubids = PublicationRep.Tendency();
		for (Long item : pubids) {
			Publication p= PublicationRep.findById(item).get();
		
			if (( p.getDate().isAfter(date)) && (p.getTime().isBefore(time))  && ( pubstendency.size()<9))
			{
				pubstendency.add(p);
			}
			
		}
		
		return pubstendency;
	}

	@Override
	public List<Publication> MostReacted() {

		List<Publication> pubreact = new ArrayList<Publication>();
		List<Long> pubids = PublicationRep.MostReacted();
		for (Long item : pubids) {
			Publication p =PublicationRep.findById(item).get();
			if  (( p.getDate().isAfter(date)) && (p.getTime().isBefore(time))  && (pubreact.size()<9) )
			{
				pubreact.add(p);
			}
			
			
		}
		return pubreact;
	}

	@Override
	public List<String> reacts(Long idpublication) {
		List<String> threevalues = new ArrayList<String>();
		List<String> values = PublicationRep.reacts(idpublication);
		
			threevalues.add(0, values.get(0));
			threevalues.add(1, values.get(1));
			threevalues.add(2, values.get(2));
		
		
		
		return threevalues;
	}

	
	

	
			

}
