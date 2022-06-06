package tn.esprit.spring.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import utils.MessageResponse;
import utils.PagingResponse;
import tn.esprit.spring.entities.Ad;
import tn.esprit.spring.entities.Cagnotte;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.User;

@Service
public interface EventService {


	public void addEvent(Event event);
	public Event updateEvent( Event event);
	Event getEventById(Long id);
	void deleteEvent(Long id);
	public MessageResponse addParticipant(Long id , List<User> participants);
	public PagingResponse get(Specification<Event> spec, HttpHeaders headers, Sort sort);

}
