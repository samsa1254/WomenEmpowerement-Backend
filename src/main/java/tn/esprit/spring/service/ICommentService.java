package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Training;

public interface ICommentService {
	public Comment add (Comment b);
	public Comment update (Comment b);
	public List<Comment> list();
	void delete(Long id);
	Comment retrieve(Long id);

}
