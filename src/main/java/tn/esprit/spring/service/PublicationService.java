package tn.esprit.spring.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Publication;

@Service
public interface PublicationService {

	public String addPub( Publication pub , int id  );
	public String updatePub ( Publication pub );
	public void deletepubById ( Long id );
	public Publication retrievebyId ( Long id );
	public List<Publication> retrieveallPubs ();
	public List<Publication> tendency ( );
	public List<Publication> MostReacted();
	public List<String> reacts( Long idpublication);
}
