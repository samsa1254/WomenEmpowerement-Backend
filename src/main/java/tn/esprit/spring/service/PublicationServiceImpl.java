package tn.esprit.spring.service;

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

}
