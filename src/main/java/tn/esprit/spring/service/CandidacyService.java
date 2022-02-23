package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Candidacy;
@Service
public interface CandidacyService {

	public Candidacy retrievebyID(Long id);
	public List<Candidacy> retrieveAll();
	public Candidacy Affect( Candidacy c , Long ido, int idu);
	public Candidacy updatereCandidacy(Candidacy cand);
	public void deleteCandidacy ( Long id );
	
}
