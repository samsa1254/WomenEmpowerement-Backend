package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Publication;
import tn.esprit.spring.entities.Reaction;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.PublicationRepository;

import tn.esprit.spring.repository.ReactionRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class ReactionServiceImpl  implements ReactionService{
	@Autowired
	private ReactionRepository reactionRep ; 
	@Autowired
	private PublicationRepository pubRep;
	@Autowired
	private UserRepository UserRep ;
	
	
	@Override
	public Reaction retrievebyID(Long id) {
		Reaction r = reactionRep.findById(id).get();
		return r;
	}
	@Override
	public List<Reaction> retrieveAll() {
		List<Reaction> reacts = (List<Reaction>) reactionRep.findAll();
		return reacts;
	}
	@Override
	public Reaction addReaction(Reaction reaction, Long idPublication , int id) {
		Publication p = pubRep.findById(idPublication).get();
		User u =UserRep.findById(id).get();
		reaction.setUser(u);
		reaction.setPublication(p);
		reactionRep.save(reaction);
		return reaction;
	}
	@Override
	public Reaction updatereReaction(Reaction reaction) {
		reactionRep.save(reaction);
		return reaction;
	}
	@Override
	public void deleteReaction(Long id) {
		reactionRep.deleteById(id);
		
	}
	
	

}
