package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Disponibilite;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AppointmentRepository;
import tn.esprit.spring.repository.DisponibiliteRepository;
import tn.esprit.spring.repository.UserRepository;
@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentRepository appRep ; 
	@Autowired
	private UserRepository expRep ;
	@Autowired
	private DisponibiliteRepository disRep ;
	@Override
	public Appointment addAppointment(Appointment app) {
		appRep.save(app);
		return app;
	}

	@Override
	public Appointment updateAppointment(Appointment app) {
		appRep.save(app);
		return app;
	}

	@Override
	public void deleteappById(int id) {
		appRep.deleteById(id);
		
	}

	@Override
	public Appointment retrievebyId(int id) {
		Appointment p = appRep.findById(id).get();
		return p ;
		}

	@Override
	public List<Appointment> retrieveallApps() {
		List<Appointment> reps = (List<Appointment>) appRep.findAll();
		return reps;
	}
	
	public void AddandAffectAppointmentoexpertanduser(Appointment appointment, int idexpert,int iduser) {		
		User e = expRep.findById(idexpert).get();
	    User u = expRep.findById(iduser).get();
	    boolean valide=false;
	    List<Disponibilite> disp=e.getDisponibilite();
	    for(Disponibilite dis:disp) {
	    	if (appointment.getDateAppointment().after(dis.getDatedebut()) && appointment.getDateAppointment().before(dis.getDatefin())&& dis.getEtat()!="booked"|| appointment.getDateAppointment()==dis.getDatedebut() && dis.getEtat()!="booked" ||appointment.getDateAppointment()==dis.getDatefin() && dis.getEtat()!="booked"   )
	    	{    
                 valide= true ; 
	    	} 	
	    }
	    if (valide==true) {
	    appointment.setUser(u);
	    appointment.setUserexpert(e);
	    for(Disponibilite dis:disp) {
	    	if (appointment.getDateAppointment().after(dis.getDatedebut()) && appointment.getDateAppointment().before(dis.getDatefin()) )
	       {
	    		
	    		dis.setEtat("booked");
	    		disRep.save(dis);
	    	} 
	    	
	    }
	    appRep.save(appointment);
	    }
		
	}  
	    




}
