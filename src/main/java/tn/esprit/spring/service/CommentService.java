package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Training;
import tn.esprit.spring.repository.CommentRepository;

@Service
public class CommentService implements ICommentService{

	@Autowired
	CommentRepository repo;
	@Override
	public Comment add(Comment b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}

	@Override
	public Comment update(Comment b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}

	@Override
	public List<Comment> list() {
		// TODO Auto-generated method stub
		return (List<Comment>) repo.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Comment retrieve(Long id) {
		// TODO Auto-generated method stub
		return  repo.findById(id).get();
	}

}
