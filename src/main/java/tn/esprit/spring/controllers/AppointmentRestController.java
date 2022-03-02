package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import tn.esprit.spring.service.AppointmentService;


@Api(tags = "Appointment Management")
@RestController
@RequestMapping("/Appointment")
public class AppointmentRestController {
    @Autowired
	private AppointmentService AppSer ;
	
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
	
	@PostMapping("/add-affectappointment/{idexpert}/{iduser})")
	@ResponseBody
	public void ajouterEtAffecterrendezvousauexpertetutilisteur( @RequestBody Appointment appointment ,@PathVariable("idexpert") int idexpert,@PathVariable("iduser") int iduser)
	{
		AppSer.AddandAffectAppointmentoexpertanduser(appointment, idexpert, iduser);
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
