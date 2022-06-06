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

import tn.esprit.spring.entities.Training;
import tn.esprit.spring.service.ITrainingService;

@RestController
@CrossOrigin

public class TrainingController {
	@Autowired
	ITrainingService its;
	
	@PostMapping ("/add-training")
	public Training add(@RequestBody  Training c) {
	Training t = its.add(c);
	return t;
	}
	
	@GetMapping("/retrieve-Training/{training-id}")
	public Training retrieve(@PathVariable("training-id") Long Id) {
		return its.retrieve(Id);
	}
	@DeleteMapping("/remove-Training/{training-id}")
	public void remove(@PathVariable("training-id") Long Id) {
	its.delete(Id);
	}
	@PutMapping("/modify-Training")
	public Training modify(@RequestBody Training t) {
	return its.update(t);
	}

}
