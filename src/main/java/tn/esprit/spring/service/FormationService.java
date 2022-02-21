package tn.esprit.spring.service;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Formation;

@Service
public interface FormationService {
public void ajouterEtAffceterFormationaFormateur(Formation formation , Long idFormateur);
public Integer getRevenuByFormation(Long idFormation);
}
