package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.CommentD;
 
@Service
public interface CommentDService {

	public CommentD AddCommentPub(CommentD commentD, Long idPublication);
	public CommentD addComment ( CommentD c);
	public CommentD updateComment(CommentD c);
	public void DeleteComment ( Long id ) ;
	public CommentD retrievebyId( Long id );
	public List<CommentD> retrieveAll ( );
}
