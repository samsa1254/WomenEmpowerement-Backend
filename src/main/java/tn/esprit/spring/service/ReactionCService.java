package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.ReactionC;

@Service
public interface ReactionCService {

	public ReactionC retrievebyID(Long id);
	public List<ReactionC> retrieveAll();
	public ReactionC addReactionC( ReactionC reactionC , Long idCom);
	
	public ReactionC updatereReactionC(ReactionC reactionC);
	public void deleteReactionC ( Long id );
}
