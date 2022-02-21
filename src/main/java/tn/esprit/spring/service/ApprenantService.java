package tn.esprit.spring.service;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Apprenant;

@Service
public interface ApprenantService {

	public void ajouterApprenant(Apprenant apprenant);
	public void affecterApprenantFormation(Long idApprenant , Long idFormation);
}
