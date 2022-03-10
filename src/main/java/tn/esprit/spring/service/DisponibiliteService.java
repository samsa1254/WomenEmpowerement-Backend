package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Disponibilite;

@Service
public interface DisponibiliteService {
	
	public Disponibilite addDisponibilite( Disponibilite app, int iduser );
	public Disponibilite updateDisponibilite ( Disponibilite app );
	public void deleteDispById ( Long id );
	public Disponibilite retrievebyId ( Long id );
	public List<Disponibilite> retrieveallDisps ();
	List<Disponibilite> findBydatedebut(Date dated);
	List<Disponibilite> findBydatefin(Date datef);
	List<Disponibilite> findByPeriod(Date dated,Date datef);
	public List<Disponibilite> getuseravailibility(int iduser);
	

}
