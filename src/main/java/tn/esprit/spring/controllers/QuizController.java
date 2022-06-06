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

import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.entities.Training;
import tn.esprit.spring.service.IQuizService;

@RestController
@CrossOrigin

public class QuizController {
	@Autowired
	IQuizService its;
	@PostMapping ("/add-quiz")
	public Quiz add(@RequestBody  Quiz c) {
	Quiz t = its.add(c);
	return t;
	}
	
	@GetMapping("/retrieve-quiz/{quiz-id}")
	public Quiz retrieve(@PathVariable("quiz-id") Long Id) {
		return its.retrieve(Id);
	}
	@DeleteMapping("/remove-quiz/{quiz-id}")
	public void remove(@PathVariable("quiz-id") Long Id) {
	its.delete(Id);
	}
	@PutMapping("/modify-quiz")
	public Quiz modify(@RequestBody Quiz t) {
	return its.update(t);
	}

}
