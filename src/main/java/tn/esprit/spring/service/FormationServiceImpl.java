package tn.esprit.spring.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Formateur;
import tn.esprit.spring.entities.Formation;
import tn.esprit.spring.repository.FormateurRepository;
import tn.esprit.spring.repository.FormationRepository;

@Service
public class FormationServiceImpl implements FormationService {

	
	@Autowired
	private FormationRepository FormationRep ;
	@Autowired
	private FormateurRepository FormateurRep ;

	@Override
	public void ajouterEtAffceterFormationaFormateur(Formation formation, Long idFormateur) {
		
		
		Formateur f =FormateurRep.findById(idFormateur).get();
	    formation.setFormateur(f);
	    FormationRep.save(formation);
		
	}

	@Override
	public Integer getRevenuByFormation(Long idFormation) {
		
		Formation f = FormationRep.findById(idFormation).get();
		Integer apps = f.getApprenants().size();
		Integer revenu = 0 ;
		revenu = apps*f.getFrais();
		return revenu;
	}
	

}
