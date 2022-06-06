package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Training;
import tn.esprit.spring.service.ICommentService;

@CrossOrigin
public class CommentController {

	@Autowired
	ICommentService its;
	
	
	@PostMapping ("/add-comment")
	public Comment add(@RequestBody  Comment c) {
	Comment t = its.add(c);
	return t;
	}
	
	@GetMapping("/retrieve-comment/{comment-id}")
	public Comment retrieve(@PathVariable("comment-id") Long Id) {
		return its.retrieve(Id);
	}
	@DeleteMapping("/remove-comment/{comment-id}")
	public void remove(@PathVariable("comment-id") Long Id) {
	its.delete(Id);
	}
	@PutMapping("/modify-comment")
	public Comment modify(@RequestBody Comment t) {
	return its.update(t);
	}
	
	
}
