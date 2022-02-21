package tn.esprit.spring.service;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Formateur;

@Service
public interface FormateurService {

	public void ajouterFormateur(Formateur formateur);
}
