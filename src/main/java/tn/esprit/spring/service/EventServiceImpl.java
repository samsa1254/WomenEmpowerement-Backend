package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Cagnotte;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.repository.CagnotteRepository;
import tn.esprit.spring.repository.EventRepository;
import org.springframework.data.domain.Pageable;

@Service
public class EventServiceImpl  implements EventService{

	@Autowired
	EventRepository eventRepository;
	@Autowired
	CagnotteRepository cagnotteRepository;
	@Autowired
	CagnotteService cs;
	
	 @Override
	 public void addEvent(Event event) {
		 
		 Cagnotte cc=event.getCagnotte();
		 cc.setEvent(event);
		 cs.addCagnotte(cc);		 
		 event = eventRepository.save(event);
		 
		 
		 }
	 
	 @Override
	 public Page<Event> getAllEvent(Pageable pageable) {
	        return eventRepository.findAll(pageable);
	    }

	  @Override
	  public Event getEventById(Long id) {
	        return eventRepository.findById(id).get();
	    }

	   @Override
	   public void deleteEvent(Long id){
		   eventRepository.deleteById(id);
	    }
	   
	   @Override
	public Page<Event> findEventByCriteria(Pageable pageable) {
		return eventRepository.findAll(pageable);
	}
	   @Override
	public Page<Event> findByTitle(String title, Pageable pageable) {
		return eventRepository.findByTitle(title, pageable);
	}
	   
	
}
