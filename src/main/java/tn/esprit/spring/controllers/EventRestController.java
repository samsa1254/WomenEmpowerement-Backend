package tn.esprit.spring.controllers;

import java.io.IOException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;

import tn.esprit.spring.entities.Candidacy;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.service.*;
import utils.PagingHeaders;
import utils.PagingResponse;
import io.swagger.annotations.Api;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import net.kaczmarzyk.spring.data.jpa.web.annotation.*;

import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;



@Api(tags = "Event Management")
@RestController
@RequestMapping("/Event")
public class EventRestController {


	@Autowired
	EventService eventService;
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@PostMapping("/addEvent")
	public ResponseEntity<Event> addEvent(@RequestBody Event event) {
		eventService.addEvent(event);
		
		return ResponseEntity.ok().body(event);
	}

	/*@GetMapping("/getAllEvent")
	public ResponseEntity<Page<Event>> getAllEvent(
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Event> evn = eventService.getAllEvent(pageable);
		return ResponseEntity.ok().body(evn);
	}*/

	@GetMapping("/getAllEvent")
	public List<Event> getAllEvent()
	{
		List<Event> c = (List<Event>) eventRepository.findAll();
		return c;
	}
	@GetMapping("/getEventById/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventById(id));
	}

	@DeleteMapping("/deleteEvent/{id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long id) {
		eventService.deleteEvent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
/*
	@GetMapping("/sort")
	public ResponseEntity<Page<Event>> findEventByCriteria(
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
			@RequestParam( required = true) String param,
			@RequestParam( required = true) Sort.Direction direction) {
		Pageable pageable = !StringUtils.hasLength(param) ? PageRequest.of(page, size)
				: PageRequest.of(page, size, direction, param);
		Page<Event> evn = eventService.getAllEvent(pageable);
		return ResponseEntity.ok().body(evn);
	}
	@GetMapping("/search/{title}")
	public ResponseEntity<Page<Event>> findByTitle(@PathVariable("title") String title, @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "10", required = false) Integer size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Event> evn = eventService.findByTitle(title,pageable);	
		return ResponseEntity.ok().body(evn);
	}
	*/
	
	
	@PostMapping("/send/{id}")
	public void sendEmail(@PathVariable("id") Long id) throws MailException {

		
		Event e = eventService.getEventById(id);
		List<User> users = e.getParticipants();
		
		for( User u: users)
		{ 
			
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(u.getEmail());
		mail.setSubject("Testing Mail API");
		mail.setText("Dear Client :"+u.getName()+" welcome in our event we wish that will donate for the womenempowerement !! ");

		javaMailSender.send(mail);
		}
	}
	
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> citiesReport() throws IOException {

		List<Event> evn = (List<Event>) eventRepository.findAll();

		ByteArrayInputStream bis = utils.GeneratePdfReport.citiesReport(evn);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

	
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	

	@Transactional
    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Event>> get(
    		@And({
                    @Spec(path = "title", params = "title", spec = Like.class),
                    @Spec(path = "type", params = "type", spec = Like.class),
                    @Spec(path = "dateDebut", params = "DateDebut", spec = Equal.class),
                    @Spec(path = "createDate", params = {"DateDebut", "dateFin"}, spec = Between.class)
            }) Specification<Event> spec,
            Sort sort,
            @RequestHeader HttpHeaders headers) {
        final PagingResponse response = eventService.get(spec, headers, sort);
        return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);
    }
	
	
	public HttpHeaders returnHttpHeaders(PagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }

}
