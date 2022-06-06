package tn.esprit.spring.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import tn.esprit.spring.entities.Disponibilite;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.AppointmentService;
import tn.esprit.spring.service.DisponibiliteService;
@CrossOrigin
@Api(tags = "Availibility Management")
@RestController
@RequestMapping("/Availibility")
public class DisponibliteRestController {
	 @Autowired
		private DisponibiliteService dispSer ;
	 @Autowired
	    private UserRepository uRep ;
		
		@GetMapping("/retrieve-all-disps")
		@ApiOperation(value = "Récupérer la liste des disponibilites ")
		@ResponseBody
		public List<Disponibilite> getApps()
		{
			List<Disponibilite> disps = dispSer.retrieveallDisps();
			return disps;
		}
		
		@GetMapping("/retrieve-disp/{idAvailibility}")
		@ApiOperation(value = "recuperer une disponibilite  ")
		@ResponseBody
		public Disponibilite getAvailiibity (@PathVariable("idAvailibility") Long iddisp)
		{
			return dispSer.retrievebyId(iddisp);   
		}
		
		@PostMapping("/add-disp")
		@ApiOperation(value = "ajouter une disponiblite ")
		@ResponseBody 
		public Disponibilite addAvailiibity(@RequestBody Disponibilite disp )
		{   SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        User u = uRep.findByLogin(auth.getName());
         int UserId = u.getIduser() ; 
			Disponibilite d= dispSer.addDisponibilite(disp,UserId);
			return d ; 
			
		}
		@DeleteMapping("/remove-disp/{idAvailibility}")
		@ApiOperation(value = "supprimer une disponibilite ")
		@ResponseBody
		public void removePub(@PathVariable("idAvailibility") Long iddisp )
		{
			dispSer.deleteDispById(iddisp);
		}
		
		@PutMapping("/modify-disp")
		@ApiOperation(value = "modifier une disponibilite ")
		@ResponseBody
		public Disponibilite modifyPublication(@RequestBody Disponibilite disp)
		{
			Disponibilite d = dispSer.updateDisponibilite(disp);
			return d ; 
		}
		
		
		@GetMapping("/retrieve-dispbyperiod/{datedebut}/{datefin}")
		@ApiOperation(value = "recuperer une disponibilite par periode  ")
		@ResponseBody
		public List<Disponibilite> getavailibilitybyperiod (@PathVariable("datedebut")@DateTimeFormat(iso=ISO.DATE) Date datedebut, @PathVariable("datefin")@DateTimeFormat(iso=ISO.DATE) Date Datefin)
		{   
			return  dispSer.findByPeriod(datedebut, Datefin);   
		}
		
		@GetMapping("/retrieve-useravailibility")
		@ApiOperation(value = "recuperer les disponibilite d'un utilisateur   ")
		@ResponseBody
		public List<Disponibilite> getuseravailibility ()
		{   SecurityContext context = SecurityContextHolder.getContext();
            Authentication auth = context.getAuthentication();
            User u = uRep.findByLogin(auth.getName());
            int UserId = u.getIduser() ; 
			return  dispSer.getuseravailibility(UserId);   
		}
		
		
}
