package tn.esprit.spring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.CommentD;
 
import tn.esprit.spring.entities.Publication;
import tn.esprit.spring.repository.CommentDRepository;
import tn.esprit.spring.repository.PublicationRepository;

@Service
public class CommentDServiceImpl  implements CommentDService{

	@Autowired
	private CommentDRepository CommentRep ;
	@Autowired
	private PublicationRepository PubRep ;
	
	@Override
	public CommentD AddCommentPub(CommentD commentD, Long idPublication) {
		
		
		Publication p =PubRep.findById(idPublication).get();
	    commentD.setPublication(p);
	    CommentRep.save(commentD);
	    return commentD ;
		
	}
	
	@Override
	public CommentD addComment(CommentD c) {
		CommentRep.save(c);
		return c ; 
	}

	@Override
	public CommentD updateComment(CommentD c) {
		CommentRep.save(c);
		return c ;
	}

	@Override
	public void DeleteComment(Long id) {
		CommentRep.deleteById(id);
		
	}

	@Override
	public CommentD retrievebyId(Long id) {
	CommentD c = CommentRep.findById(id).get();
	return c ; 
	}

	@Override
	public List<CommentD> retrieveAll() {
		List<CommentD> cmnts = (List<CommentD>) CommentRep.findAll();
		return cmnts ;
	}

	
}
