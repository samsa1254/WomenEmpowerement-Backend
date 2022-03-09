package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Report;
@Service
public interface ReportService{
	public Report addReport( Report rep );
	public Report updatereport ( Report rep );
	public void deleterepById ( Long id );
	public Report retrievebyId ( Long id );
	public List<Report> retrieveallreps ();
	public Appointment treataReportbyMakingappointment(Long id, int iduser2,int user3,Appointment a);
	public void treataReportbyblockinguser(Long id,int iduser);
	public void treataReportbyunblockinguser(Long id,int iduser);
	

}
