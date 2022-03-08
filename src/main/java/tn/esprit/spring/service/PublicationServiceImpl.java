package tn.esprit.spring.service;

import java.util.ArrayList;


import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Publication;
import tn.esprit.spring.repository.PublicationRepository;

@Service
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private PublicationRepository PublicationRep ; 
	@Override
	public Publication addPub(Publication pub) {
		PublicationRep.save(pub);
		return pub;
	}

	@Override
	public Publication updatePub(Publication pub) {
		PublicationRep.save(pub);
		return pub;
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
			pubstendency.add(p);
			
		}
		
		return pubstendency;
	}

	@Override
	public List<Publication> MostReacted() {
		List<Publication> pubreact = new ArrayList<Publication>();
		List<Long> pubids = PublicationRep.MostReacted();
		for (Long item : pubids) {
			Publication p =PublicationRep.findById(item).get();
			pubreact.add(p);
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
