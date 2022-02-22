package tn.esprit.spring.service;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.ReactionC;

@Service
public interface ReactionCService {

	public ReactionC addReactionC( ReactionC reactionC , Long idCom);
	
	public ReactionC updatereReactionC(ReactionC reactionC);
	public void deleteReactionC ( Long id );
}
