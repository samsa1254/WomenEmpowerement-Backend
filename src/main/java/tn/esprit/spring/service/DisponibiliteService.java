package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Disponibilite;

@Service
public interface DisponibiliteService {
	
	public Disponibilite addDisponibilite( Disponibilite app );
	public Disponibilite updateDisponibilite ( Disponibilite app );
	public void deleteDispById ( Long id );
	public Disponibilite retrievebyId ( Long id );
	public List<Disponibilite> retrieveallDisps ();

}
