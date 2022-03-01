package tn.esprit.spring.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Event;

@Service
public interface EventService {

	public void addEvent(Event event);
	Page<Event> getAllEvent( Pageable pageable);
	Event getEventById(Long id);
	void deleteEvent(Long id);
	Page<Event> findEventByCriteria(Pageable pageable);
	Page<Event> findByTitle(String title, Pageable pageable);

}
