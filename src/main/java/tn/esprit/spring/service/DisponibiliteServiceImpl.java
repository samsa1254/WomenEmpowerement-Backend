package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Disponibilite;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AppointmentRepository;
import tn.esprit.spring.repository.DisponibiliteRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class DisponibiliteServiceImpl implements DisponibiliteService {
	@Autowired
	private DisponibiliteRepository disRep ; 
	@Autowired
	private UserRepository uRep ;
	@Override
	
	public Disponibilite addDisponibilite(Disponibilite disp,int iduser) {
		User u=uRep.findById(iduser).get();
		disp.setUser(u);
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

	@Override
	public List<Disponibilite> findBydatedebut(Date dated) {
		
		return disRep.findBydatedebut(dated);
	}

	@Override
	public List<Disponibilite> findBydatefin(Date datef) {
		return disRep.findBydatefin(datef);
	}

	@Override
	public List<Disponibilite> findByPeriod(Date dated, Date datef) {		
		return disRep.findByDatedebutAfterAndDatefinBefore(dated, datef) ;
		
		
	}

	@Override
	public List<Disponibilite> getuseravailibility(int iduser) {
		User u=uRep.findById(iduser).get();
		List<Disponibilite> d=u.getDisponibilite();
		return d;
	}

}
