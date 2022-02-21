package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.repository.AppointmentRepository;
@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentRepository appRep ; 
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
	public void deleteappById(Long id) {
		appRep.deleteById(id);
		
	}

	@Override
	public Appointment retrievebyId(Long id) {
		Appointment p = appRep.findById(id).get();
		return p ;
		}

	@Override
	public List<Appointment> retrieveallApps() {
		List<Appointment> reps = (List<Appointment>) appRep.findAll();
		return reps;
	}


}
