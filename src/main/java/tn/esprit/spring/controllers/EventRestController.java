package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import tn.esprit.spring.entities.Event;
import tn.esprit.spring.service.EventService;


@Api(tags = "Event Management")

@RestController
@RequestMapping("/Event")
public class EventRestController {


	@Autowired
	 private EventService EventSer ;
	
	
	@GetMapping("/retrieve-all-events")
	@ApiOperation(value = "Récupérer la liste des events ")
	@ResponseBody
	public List<Event> getEvents()
	{
		List<Event> events = EventSer.getAllEvent();
		return events;
	}
	
	@GetMapping("/retrieve-event/{id}")
	@ApiOperation(value = "recuperer un event  ")
	@ResponseBody
	public Event getEvent (@PathVariable("id") Long id)
	{
		return EventSer.getEventById(id);   
	}
	
	
	@PostMapping("/add-event")
	@ApiOperation(value = "ajouter un event ")
	@ResponseBody 
	public void addevent(@RequestBody Event event )
	{
		EventSer.addEvent(event);
		
		
	}
	
	@DeleteMapping("/remove-event/{id}")
	@ApiOperation(value = "supprimer un event ")
	@ResponseBody
	public void removeEvent(@PathVariable("id") Long id )
	{
		EventSer.deleteEvent(id);
	}
	
	@PutMapping("/modify-event")
	@ApiOperation(value = "modifier un event ")
	@ResponseBody
	public void modifyevent(@RequestBody Event event)
	{
		EventSer.updateEvent(event);
		 
	}
}
