package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Disponibilite;
import tn.esprit.spring.repository.AppointmentRepository;
import tn.esprit.spring.repository.DisponibiliteRepository;

@Service
public class DisponibiliteServiceImpl implements DisponibiliteService {
	@Autowired
	private DisponibiliteRepository disRep ; 
	@Override
	public Disponibilite addDisponibilite(Disponibilite disp) {
		disRep.save(disp);
		return disp;
	}

	@Override
	public Disponibilite updateDisponibilite(Disponibilite disp) {
		disRep.save(disp);
		return disp;
	}

	@Override
	public void deleteDispById(Long id) {
		disRep.deleteById(id);
		
	}

	@Override
	public Disponibilite retrievebyId(Long id) {
		Disponibilite d = disRep.findById(id).get();
		return d ;
		}

	@Override
	public List<Disponibilite> retrieveallDisps() {
		List<Disponibilite> disps = (List<Disponibilite>) disRep.findAll();
		return disps;
	}

}
