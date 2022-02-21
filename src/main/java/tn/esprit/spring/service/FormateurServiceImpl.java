package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Formateur;
import tn.esprit.spring.repository.FormateurRepository;

@Service
public class FormateurServiceImpl implements FormateurService{

	@Autowired
	private FormateurRepository FormateurRep ;
	@Override
	public void ajouterFormateur(Formateur formateur) {
		FormateurRep.save(formateur);
		
	}

}
