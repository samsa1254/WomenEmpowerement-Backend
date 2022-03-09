package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.forbiden;
import tn.esprit.spring.repository.forbidenRepository;

@Service
public class forbidenServiceImpl implements forbidenService {

	@Autowired
	private forbidenRepository forbidenRep ; 
	@Override
	public void addforbidenword(forbiden forbiden) {
		forbidenRep.save(forbiden);
		
		
	}

	@Override
	public void updateforbidenword(forbiden forbiden) {
		forbidenRep.save(forbiden);
		
	}

	@Override
	public forbiden getforbidenword(Long id) {
		
		return forbidenRep.findById(id).get();
	}

	@Override
	public void deleteforbidenword(Long id) {
		forbidenRep.deleteById(id);
		
	}

	@Override
	public List<forbiden> getall() {
		
		return (List<forbiden>)forbidenRep.findAll();
		
		
		
		
	}

	
}
