package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.mail.EmailControllers;
import tn.esprit.spring.repository.CagnotteRepository;
import tn.esprit.spring.repository.EventRepository;
import utils.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import javax.persistence.EntityNotFoundException;
import java.util.Objects;


@Service
public class EventServiceImpl  implements EventService{

	@Autowired
	EventRepository eventRepository;
	@Autowired
	CagnotteRepository cagnotteRepository;
	@Autowired
	CagnotteService cs;
	@Autowired
	EmailControllers EC;
	
	
	@Override
	 public void addEvent(Event event) {		 		 
		 event = eventRepository.save(event);
		 }
	 
	@Override
	public Event updateEvent( Event event) {
		eventRepository.save(event);
		return null;
	}
	
	  @Override
	  public Event getEventById(Long id) {
			Event e = eventRepository.findById(id).get();
	        return e;
	    }

	   @Override
	   public void deleteEvent(Long id){
		   eventRepository.deleteById(id);
	    }
	   
	   @Override
	   public MessageResponse addParticipant(Long id , List<User> participants) {
		   
		   MessageResponse response = new MessageResponse();
		    Optional <Event> eventOptional = eventRepository.findById(id);
		    if(!eventOptional.isPresent()) {
		    	String message = "Event with id" +id+ "does not exist";
		    	response.setMessage(message);
		    	response.setResponse(null);
		    	response.setError(true);
		    	return response;
		    }
		    Event event = eventOptional.get();
		    event.getParticipants().addAll(participants); 
		    eventRepository.save(event);
		    
		    response.setMessage("ok");
		    response.setResponse(event);
		    response.setError(false);
		    return response;
	   }

	    /**
	     * delete element
	     *
	     * @param id element ID
	     * @throws EntityNotFoundException Exception when retrieve entity
	     */
	    public void delete(Long id) {
	        Event entity = eventRepository.findById(id)
	                                  .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity car (%s = %s).", "id", id)));
	        eventRepository.delete(entity);
	    }

	    /**
	     * @param id element ID
	     * @return element
	     * @throws EntityNotFoundException Exception when retrieve element
	     */
	    public Event get(Long id) {
	        return eventRepository.findById(id)
	                            .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity car (%s = %s).", "id", id)));
	    }

	    /**
	     * get element using Criteria.
	     *
	     * @param spec    *
	     * @param headers pagination data
	     * @param sort    sort criteria
	     * @return retrieve elements with pagination
	     */
	    public PagingResponse get(Specification<Event> spec, HttpHeaders headers, Sort sort) {
	        if (isRequestPaged(headers)) {
	            return get(spec, buildPageRequest(headers, sort));
	        } else {
	            List<Event> entities = get(spec, sort);
	            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
	        }
	    }

	    private boolean isRequestPaged(HttpHeaders headers) {
	        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
	    }

	    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
	        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
	        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
	        return PageRequest.of(page, size, sort);
	    }

	    /**
	     * get elements using Criteria.
	     *
	     * @param spec     *
	     * @param pageable pagination data
	     * @return retrieve elements with pagination
	     */
	    public PagingResponse get(Specification<Event> spec, Pageable pageable) {
	        Page<Event> page = eventRepository.findAll(spec, pageable);
	        List<Event> content = page.getContent();
	        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
	    }

	    /**
	     * get elements using Criteria.
	     *
	     * @param spec *
	     * @return elements
	     */
	    public List<Event> get(Specification<Event> spec, Sort sort) {
	        return eventRepository.findAll(spec, sort);
	    }

	    
	  

}
