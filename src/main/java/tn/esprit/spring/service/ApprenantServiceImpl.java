package tn.esprit.spring.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Apprenant;
import tn.esprit.spring.entities.Formation;
import tn.esprit.spring.repository.ApprenantRepository;
import tn.esprit.spring.repository.FormationRepository;


@Service
public class ApprenantServiceImpl  implements ApprenantService{

	
	@Autowired
	private ApprenantRepository ApprenantRep;
	@Autowired
	private FormationRepository FormationRep ;
	@Override
	public void ajouterApprenant(Apprenant apprenant) {
		ApprenantRep.save(apprenant);
		
	}
	@Override
	public void affecterApprenantFormation(Long idApprenant, Long idFormation) {
		Apprenant a = ApprenantRep.findById(idApprenant).get();
		Formation f = FormationRep.findById(idFormation).get();
		
		List<Apprenant> app = f.getApprenants();
		if(app.size() <= f.getNbrMaxParticipants())
		{
			app.add(a);
			f.setApprenants(app);
			FormationRep.save(f);
			
		}
		else 
		{
			System.out.println(" nbr maximal est atteint ");
		}
		
		
		
	}
	
	//@Scheduled(cron = "*/30 * * * * *")
	/*void nbreApprenantByFormation() {
		List<Formation> frms = (List<Formation>) FormationRep.findAll();
		for (Formation item : frms) {
			//System.out.println("la formation : "+item.getTitre()+" contient "+item.getApprenants().size()+"apprenants  ");
			
		}

	}*/

}
