package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Event;
import tn.esprit.spring.repository.EventRepository;

@Service
public class EventServiceImpl  implements EventService{

	
	@Autowired
	EventRepository EventRep;
	@Override
	public void addEvent(Event event) {
		EventRep.save(event);
		
	}

	@Override
	public void updateEvent(Event event) {
		EventRep.save(event);
		
	}

	@Override
	public List<Event> getAllEvent() {
		List<Event> events = (List<Event>) EventRep.findAll();
		return events;
	}

	@Override
	public Event getEventById(Long id) {
		Event e = EventRep.findById(id).get();
		return e;
	}

	@Override
	public void deleteEvent(Long id) {
		EventRep.deleteById(id);
		
	}

}
