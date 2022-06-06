package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Training;
import tn.esprit.spring.service.IQuestionService;

@RestController
@CrossOrigin

public class QuestionController {

	@Autowired
	IQuestionService its;
	
	@PostMapping ("/add-question")
	public Question add(@RequestBody  Question c) {
		Question t = its.add(c);
	return t;
	}
	
	@GetMapping("/retrieve-question/{question-id}")
	public Question retrieve(@PathVariable("training-id") Long Id) {
		return its.retrieve(Id);
	}
	@DeleteMapping("/remove-question/{question-id}")
	public void remove(@PathVariable("question-id") Long Id) {
	its.delete(Id);
	}
	@PutMapping("/modify-question")
	public Question modify(@RequestBody Question t) {
	return its.update(t);
	}

}
