package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.CommentD;

import tn.esprit.spring.entities.ReactionC;
import tn.esprit.spring.repository.CommentDRepository;
import tn.esprit.spring.repository.ReactionCRepository;

@Service
public class ReactionCServiceImpl implements ReactionCService{

	@Autowired
	private ReactionCRepository reactioncRep ; 
	@Autowired
	private CommentDRepository commentdRep;
	

	@Override
	public ReactionC updatereReactionC(ReactionC reactionC) {
		reactioncRep.save(reactionC);
		return reactionC;
	}

	@Override
	public void deleteReactionC(Long id) {
	reactioncRep.deleteById(id);
		
	}

	@Override
	public ReactionC addReactionC(ReactionC reactionC, Long idCom) {
		CommentD c =commentdRep.findById(idCom).get();
	    reactionC.setCommentd(c);
	    reactioncRep.save(reactionC);
	    return reactionC ;
	}

	@Override
	public ReactionC retrievebyID(Long id) {
		return reactioncRep.findById(id).get();
	}

	@Override
	public List<ReactionC> retrieveAll() {
		List<ReactionC> reacts = (List<ReactionC>) reactioncRep.findAll();
		return reacts;
	}

}
