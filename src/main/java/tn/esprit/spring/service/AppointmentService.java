package tn.esprit.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Appointment;


@Service
public interface AppointmentService {
	
	public Appointment addAppointment( Appointment app );
	public Appointment updateAppointment ( Appointment app );
	public void deleteappById ( int id );
	public Appointment retrievebyId ( int id );
	public List<Appointment> retrieveallApps ();
	public void AddandAffectAppointmentoexpertanduser(Appointment appointment, int idexpert,int iduser);
	public List<Appointment> getuserappointments(int iduser);
	public List<Appointment> getexpertappointments(int iduser);
	public List<Appointment> getadminappointments(int iduser);
		

}
