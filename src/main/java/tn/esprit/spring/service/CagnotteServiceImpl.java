package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Cagnotte;
import tn.esprit.spring.repository.CagnotteRepository;

@Service
public class CagnotteServiceImpl implements CagnotteService{

	@Autowired
	CagnotteRepository CagnotteRep ;
	@Override
	public void addCagnotte(Cagnotte cagnotte) {
		CagnotteRep.save(cagnotte);
		
	}

	@Override
	public void updateCagnotte(Cagnotte cagnotte) {
		CagnotteRep.save(cagnotte);
		
	}

	@Override
	public List<Cagnotte> getAllCagnotte() {
		List<Cagnotte> cagnottes = (List<Cagnotte>) CagnotteRep.findAll();
		return cagnottes;
	}

	@Override
	public Cagnotte getCagnotteById(Long id) {
		Cagnotte c = CagnotteRep.findById(id).get();
		return c ;
	}

	@Override
	public void deleteCagnotte(Long id) {
		CagnotteRep.deleteById(id);
		
	}

}
