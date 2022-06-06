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

import tn.esprit.spring.entities.Response;
import tn.esprit.spring.entities.Training;
import tn.esprit.spring.service.IResponceService;

@RestController
@CrossOrigin

public class ResponceController {
	@Autowired
	IResponceService its;
	
	@PostMapping ("/add-responce")
	public Response add(@RequestBody  Response c) {
		Response t= its.add(c);
	return t;
	}
	
	@GetMapping("/retrieve-responce/{responce-id}")
	public Response retrieve(@PathVariable("responce-id") Long Id) {
		return its.retrieve(Id);
	}
	@DeleteMapping("/remove-responce/{responce-id}")
	public void remove(@PathVariable("responce-id") Long Id) {
	its.delete(Id);
	}
	@PutMapping("/modify-responce")
	public Response modify(@RequestBody Response t) {
	return its.update(t);
	}

}
