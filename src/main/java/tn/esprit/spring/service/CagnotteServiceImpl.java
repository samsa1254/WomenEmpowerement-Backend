package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Cagnotte;
import tn.esprit.spring.repository.CagnotteRepository;


@Service
public class CagnotteServiceImpl implements CagnotteService{

	 @Autowired
	 private CagnotteRepository cagnotteRepository;
	 	 
	 
	 @Override
	 public void addCagnotte(Cagnotte cagnotte) {
		 cagnotteRepository.save(cagnotte);
	 }
	 
	 
	 @Override
	 	public List<Cagnotte> getAllCagnotte() {
	        return cagnotteRepository.findAll();
	    }

	    @Override
	    public Cagnotte getCagnotteById(Long id) {
	        return cagnotteRepository.findById(id).get();
	    }

	    @Override
	    public void deleteCagnotte(Long id){
	    	cagnotteRepository.deleteById(id);
	    }
	    

	

}
