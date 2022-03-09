package tn.esprit.spring.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Candidacy;
@Service
public interface CandidacyService {

	public Candidacy retrievebyID(Long id);
	public List<Candidacy> retrieveAll();
	public Candidacy Affect( Candidacy c , Long ido, int idu);
	public Candidacy updatereCandidacy(Candidacy cand);
	public void deleteCandidacy ( Long id );
	public List<Candidacy> getCandidacyByOffer(Long idoff);	
	public List<Candidacy> getCandidacyByuser(int idu);
	public List<Candidacy> FilterByState(String name , String state);
	public Candidacy ApproveCandid ( Long id, int s );
	
	
}
