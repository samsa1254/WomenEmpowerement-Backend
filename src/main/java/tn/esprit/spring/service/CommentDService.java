package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.CommentD;
 
@Service
public interface CommentDService {

	public String AddCommentPub(CommentD commentD, Long idPublication , int id);
	public CommentD addComment ( CommentD c);
	public String updateComment(CommentD c);
	public void DeleteComment ( Long id ) ;
	public CommentD retrievebyId( Long id );
	public List<CommentD> retrieveAll ( );
}
