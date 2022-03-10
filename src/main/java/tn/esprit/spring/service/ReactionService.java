package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Reaction;

@Service
public interface ReactionService {
	public Reaction retrievebyID(Long id);
	public List<Reaction> retrieveAll();
	public Reaction addReaction( Reaction reaction , Long idPublication , int id );
	
	public Reaction updatereReaction(Reaction reaction);
	public void deleteReaction ( Long id );
}
