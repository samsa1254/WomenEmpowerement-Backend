package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Appointment;

@Service
public interface AppointmentService {
	
	public Appointment addAppointment( Appointment app );
	public Appointment updateAppointment ( Appointment app );
	public void deleteappById ( Long id );
	public Appointment retrievebyId ( Long id );
	public List<Appointment> retrieveallApps ();

}
