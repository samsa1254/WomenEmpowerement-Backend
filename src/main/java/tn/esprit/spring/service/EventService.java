package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Event;

@Service
public interface EventService {

	public void addEvent(Event event);
	public void updateEvent( Event event);
	List<Event> getAllEvent();
	Event getEventById(Long id);
	void deleteEvent(Long id);
}
