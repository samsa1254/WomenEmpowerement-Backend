package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import tn.esprit.spring.entities.Disponibilite;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.SMS;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ReportRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.ReportService;
import tn.esprit.spring.service.SmsService;

@Api(tags = "Report Management")
@RestController
@RequestMapping("/Report")
@CrossOrigin
public class ReportRestController {

	@Autowired
	 private ReportService repSer ;
	@Autowired
	 private UserRepository urep ;
	@Autowired
	 private SmsService sSer;
	@Autowired
	 private ReportRepository rRep;
	
	@GetMapping("/retrieve-all-reps")
	@ApiOperation(value = "Récupérer la liste des Reports ")
	@ResponseBody
	public List<Report> getReports()
	{
		List<Report> reps = repSer.retrieveallreps();
		return reps;
	}
	
	@GetMapping("/retrieve-rep/{idReport}")
	@ApiOperation(value = "recuperer un Report  ")
	@ResponseBody
	public Report getReport (@PathVariable("idReport") Long idrep)
	{
		return repSer.retrievebyId(idrep);   
	}
	
	@PostMapping("/add-rep")
	@ApiOperation(value = "ajouter un Report ")
	@ResponseBody 
	public Report addReport(@RequestBody Report rep )
	{   SecurityContext context = SecurityContextHolder.getContext();
    Authentication auth = context.getAuthentication();
    User u = urep.findByLogin(auth.getName());
    int UserId = u.getIduser() ; 
		Report r= repSer.addReport(rep,UserId);
		return r ; 
		
	}
	@DeleteMapping("/remove-rep/{idReport}")
	@ApiOperation(value = "supprimer un Report ")
	@ResponseBody
	public void removerep(@PathVariable("idReport") Long idreport )
	{
		repSer.deleterepById(idreport);
	}
	
	@PutMapping("/modify-rep")
	@ApiOperation(value = "modifier un Report ")
	@ResponseBody
	public Report modifyReport(@RequestBody Report rep)
	{
		Report r = repSer.updatereport(rep);
		return r ; 
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/treatreport-bymakingappointment/{reportid}/{iduser2}/{iduser3}")
	@ResponseBody
	public void ajouterEtAffceterFormationaFormateur( @RequestBody Appointment appointment ,@PathVariable("iduser2") int iduser2,@PathVariable("iduser3") int iduser3,@PathVariable("reportid") Long idreport, SMS sms1,SMS sms2,SMS sms3)
	{  Report r=rRep.findById(idreport).get();
		User u=urep.findById(r.getUser().getIduser()).get();
        User e=urep.findById(iduser2).get();
        User a=urep.findById(iduser3).get();
    System.out.println(appointment.getDateAppointment());
    appointment.getDateAppointment().setHours(appointment.getDateAppointment().getHours()-1);
	sms1.setMessage("Mrs "+u.getName()+"\n"+"You have an appointment with Mr/Mrs"+" "+e.getName()+" and "+a.getName()+" at: "+appointment.getDateAppointment());
    sms1.setTo(u.getExpertnumber());
    System.out.println(u.getExpertnumber());
    sms2.setTo(e.getExpertnumber());
    sms3.setTo(a.getExpertnumber());
    sSer.send(sms1);
    sms2.setMessage("Mrs "+e.getName()+"\n"+"You have an appointment with Mr/Mrs"+" "+u.getName()+" and "+a.getName()+" at: "+appointment.getDateAppointment());
	sSer.send(sms2);
	sms3.setMessage("Mrs "+a.getName()+"\n"+"You have an appointment with Mr/Mrs"+" "+u.getName()+" and "+e.getName()+" at: "+appointment.getDateAppointment());
	sSer.send(sms3);
	 appointment.getDateAppointment().setHours(appointment.getDateAppointment().getHours()+1);
		repSer.treataReportbyMakingappointment(idreport,iduser2,iduser3,appointment);
	}
	
	@PutMapping("/treatreport-byblockinguser/{idU}")
	@ResponseBody
	public Report treatreportbyblockinguser( @RequestBody Report rep,@PathVariable("idU") int iduser)
	{   
		repSer.treataReportbyblockinguser(rep, iduser);
		repSer.updatereport(rep);
		return rep ;
	}
	
	@PutMapping("/treatreport-byunblockinguser/{idU}")
	@ResponseBody
	public void treatreportbyunblockinguser( @RequestBody Report rep,@PathVariable("idU") int iduser)
	{   
		repSer.treataReportbyunblockinguser(rep, iduser);
	}
	
	@GetMapping("/retrieve-userReports/{id}")
	@ApiOperation(value = "recuperer les disponibilite d'un utilisateur   ")
	@ResponseBody
	public List<Report> getuserreports (@PathVariable("id") int id)
	{   //SecurityContext context = SecurityContextHolder.getContext();
      //  Authentication auth = context.getAuthentication();
      //  User u = urep.findByLogin(auth.getName());
      //  int UserId = u.getIduser() ; 
		return  repSer.getuserreports(id);   
	}

}