package tn.esprit.spring.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Formation;
import tn.esprit.spring.entities.SMS;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.mail.EmailControllers;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.AppointmentService;
import tn.esprit.spring.service.IUserService;
import tn.esprit.spring.service.SmsService;


@Api(tags = "Appointment Management")
@RestController
@RequestMapping("/Appointment")
@CrossOrigin

public class AppointmentRestController {
    @Autowired
	private AppointmentService AppSer ;
    @Autowired
    private SmsService sSer;
    @Autowired
    private IUserService uSer ;
    @Autowired 
    private UserRepository urep ;
    @Autowired
    private EmailControllers ec;
	
	@GetMapping("/retrieve-all-apps")
	@ApiOperation(value = "Récupérer la liste des rendez-vous ")
	@ResponseBody
	public List<Appointment> getApps()
	{
		List<Appointment> apps = AppSer.retrieveallApps();
		return apps;
	}
	
	@GetMapping("/retrieve-app/{idAppointment}")
	@ApiOperation(value = "recuperer un rendez-vous  ")
	@ResponseBody
	public Appointment getAppointment (@PathVariable("idAppointment") int idApp)
	{
		return AppSer.retrievebyId(idApp);   
	}
	
	@PostMapping("/add-app")
	@ApiOperation(value = "ajouter un Rendez-vous ")
	@ResponseBody 
	public Appointment addAppointment(@RequestBody Appointment app )
	{
		Appointment a= AppSer.addAppointment(app);
		return a ; 
		
	}
	@DeleteMapping("/remove-app/{idAppointment}")
	@ApiOperation(value = "supprimer un rendez-vous ")
	@ResponseBody
	public void removePub(@PathVariable("idAppointment") int idapp )
	{
		AppSer.deleteappById(idapp);
	}
	
	@PutMapping("/modify-app")
	@ApiOperation(value = "modifier un rendez-vous ")
	@ResponseBody
	public Appointment modifyPublication(@RequestBody Appointment app)
	{
		Appointment a = AppSer.updateAppointment(app);
		return a ; 
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/add-affectappointment/{idexpert})")
	@ResponseBody
	public void ajouterEtAffecterrendezvousauexpertetutilisteur( @RequestBody Appointment appointment ,@PathVariable("idexpert") int idexpert, SMS sms1,SMS sms2)
	{   SecurityContext context = SecurityContextHolder.getContext();
    Authentication auth = context.getAuthentication();
    User us = urep.findByLogin(auth.getName());
    int UserId = us.getIduser() ; 
		
		User u=urep.findById(UserId).get();
	    User e=urep.findById(idexpert).get();
	    System.out.println(appointment.getDateAppointment());
	    appointment.getDateAppointment().setHours(appointment.getDateAppointment().getHours()-1);
		sms1.setMessage("Mrs/Mr "+u.getName()+"\n"+"You have an appointment with Mr/Mrs"+" "+e.getName()+" at: "+appointment.getDateAppointment());
	    sms1.setTo(u.getExpertnumber());
	    System.out.println(u.getExpertnumber());
	    sms2.setTo(e.getExpertnumber());
	    sSer.send(sms1);
	    sms2.setMessage("Mrs/Mr "+e.getName()+"\n"+"You have an appointment with Mr/Mrs"+" "+u.getName()+" at: "+appointment.getDateAppointment());
		sSer.send(sms2);
		//ec.AppointmentMail(u.getEmail(), u, e, appointment);
		 appointment.getDateAppointment().setHours(appointment.getDateAppointment().getHours()+1);
		AppSer.AddandAffectAppointmentoexpertanduser(appointment, idexpert, UserId);
	}
	
	
	@GetMapping("/retrieve-userapp/{iduser}")
	@ApiOperation(value = "recuperer les rendez-vous de l'utilisateur  ")
	@ResponseBody
	public List<Appointment> getuserAppointment (@PathVariable("iduser") int iduser)
	{
		return AppSer.getuserappointments(iduser);   
	}
	
	@GetMapping("/retrieve-expertapp/{idexp}")
	@ApiOperation(value = "recuperer les rendez-vous de l'expert  ")
	@ResponseBody
	public List<Appointment> getexpAppointment (@PathVariable("idexp") int idexp)
	{
		return AppSer.getexpertappointments(idexp);   
	}
	
	@GetMapping("/retrieve-adminapp/{idadmin}")
	@ApiOperation(value = "recuperer les rendez-vous de l'admin  ")
	@ResponseBody
	public List<Appointment> getadminAppointment (@PathVariable("idadmin") int idadmin)
	{
		return AppSer.getexpertappointments(idadmin);   
	}
	
	
}
