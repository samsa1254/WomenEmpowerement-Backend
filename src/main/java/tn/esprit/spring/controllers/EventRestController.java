package tn.esprit.spring.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayInputStream;

import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.service.*;
import io.swagger.annotations.Api;


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

	@GetMapping("/getAllEvent")
	public ResponseEntity<Page<Event>> getAllEvent(
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Event> evn = eventService.getAllEvent(pageable);
		return ResponseEntity.ok().body(evn);
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

		ByteArrayInputStream bis = tn.esprit.spring.PDF.GeneratePdfReport.citiesReport(evn);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

	
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	
}
