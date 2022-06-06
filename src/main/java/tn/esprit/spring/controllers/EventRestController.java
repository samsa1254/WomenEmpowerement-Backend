package tn.esprit.spring.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.service.*;
import utils.MessageResponse;
import utils.PagingHeaders;
import utils.PagingResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import net.kaczmarzyk.spring.data.jpa.web.annotation.*;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import org.springframework.data.domain.Sort;




@CrossOrigin(origins = "http://localhost:4200")
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
	
	@PostMapping("/addParticipant/{id}")
	 public ResponseEntity<MessageResponse> addParticipant(@PathVariable("id") Long id ,@RequestBody List<User> participants) {
		return ResponseEntity.ok().body(eventService.addParticipant(id, participants));
	}
	

	@PutMapping("/modify-event")
	@ApiOperation(value = "modifier un event ")
	@ResponseBody
	public Event modifyEvent(@RequestBody Event event)
	{
		Event event1 = eventService.updateEvent(event);
		return  event1; 
	}

	/*@PutMapping("/Update-event")
	@ApiOperation(value = "update un event ")
	@ResponseBody
	public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event EventDetails){
		Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not exist with id :" + id));
		event.setDateDebut(EventDetails.getDateDebut());
		event.setDateFin(EventDetails.getDateFin());
		event.setDescription(EventDetails.getDescription());
		event.setTitle(EventDetails.getTitle());
		event.setType(EventDetails.getType());
		
		Event updateEvent = eventRepository.save(event);
		return ResponseEntity.ok(updateEvent);
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
