package tn.esprit.spring.service;


import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Cagnotte;

@Service
public interface CagnotteService {

	public void addCagnotte(Cagnotte cagnotte);
	List<Cagnotte> getAllCagnotte();
	Cagnotte getCagnotteById(Long id);
	void deleteCagnotte(Long id);
   
}
